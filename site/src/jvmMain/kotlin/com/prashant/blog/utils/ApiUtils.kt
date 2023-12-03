package com.prashant.blog.utils

import com.prashant.blog.constanst.apiendpoints.ApiEndpointConstants.json
import com.prashant.blog.repo.MongoDB
import com.prashant.blog.sealeds.JVMApiResponse
import com.prashant.blog.sealeds.MongoResponse
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString

/**
 * Utility class for common API-related operations, including handling errors,
 * processing MongoDB responses, and providing convenient extension functions.
 *
 * @property json JSON serialization and deserialization utility.
 */
object ApiUtils {
    /**
     * Decodes a JSON string into an object of the specified type [Generic].
     *
     * @param Generic the type to decode the JSON string into.
     * @return The decoded object of type [Generic].
     */
    inline fun <reified Generic> String.decodeFromString(): Generic =
        json.decodeFromString<Generic>(this)

    /**
     * Creates a bad method request response with the given HTTP [code] and [errorMessage].
     *
     * @param code The HTTP status code (default is 400 for Bad Request).
     * @param errorMessage The error message associated with the bad request.
     * @return A Pair containing the HTTP status code and the corresponding error message.
     */
    fun badMethodRequest(
        code: Int = 400,
        errorMessage: String = "Bad request method POST"
    ) = Pair(
        code,
        json.encodeToString(
            JVMApiResponse.Error(
                errorMessage = errorMessage,
                statusCode = code
            )
        )
    )

    /**
     * Creates a save error response with the given HTTP [code] and [errorMessage].
     *
     * @param code The HTTP status code (default is 404 for Not Found).
     * @param errorMessage The error message associated with the save error.
     * @return A Pair containing the HTTP status code and the corresponding error message.
     */
    fun saveErrorResponse(
        code: Int = 404,
        errorMessage: String
    ) = badMethodRequest(code, errorMessage)

    /**
     * Handles MongoDB empty responses and generates an appropriate HTTP response.
     *
     * @param mongoResponse The MongoDB response to handle.
     * @param successMessage The success message to include in the response.
     * @param elseErrorMessage The default error message if none is provided in [mongoResponse].
     * @return A Pair containing the HTTP status code and the corresponding response message.
     */
    fun <Generic> handleMongoEmptyResponse(
        mongoResponse: MongoResponse<Generic>,
        successMessage: String,
        elseErrorMessage: String = ""
    ): Pair<Int, String> = when (mongoResponse) {
        is MongoResponse.Error -> saveErrorResponse(
            code = 404,
            errorMessage = mongoResponse.error ?: elseErrorMessage
        )

        is MongoResponse.Success -> Pair(
            200,
            json.encodeToString(
                JVMApiResponse.EmptyBodySuccess(
                    responseMessage = successMessage,
                    statusCode = 200
                )
            )
        )
    }

    /**
     * Sets the HTTP response body for the current API context.
     *
     * @param response The Pair containing the HTTP status code and response message.
     */
    fun ApiContext.setBody(response: Pair<Int, String>) {
        res.apply {
            status = response.first
            setBodyText(response.second)
        }
    }

    /**
     * Retrieves the MongoDB instance from the current API context data.
     *
     * @return The MongoDB instance.
     */
    fun ApiContext.mongoDB(): MongoDB = data.getValue<MongoDB>()

    /**
     * Executes a block of code within a try-catch block, handling exceptions
     * and returning an appropriate HTTP response in case of an error.
     *
     * @param errorMessage The default error message if none is provided by the caught exception.
     * @param code The default HTTP status code to use in case of an error (default is 400 for Bad Request).
     * @param block The suspend function to execute within the try-catch block.
     * @return A Pair containing the HTTP status code and the corresponding response message.
     */
    suspend fun tryCatchBlock(
        errorMessage: String? = null,
        code: Int = 400,
        block: suspend () -> Pair<Int, String>
    ): Pair<Int, String> = try {
        block()
    } catch (ex: Exception) {
        badMethodRequest(
            code = code,
            errorMessage = errorMessage ?: ex.localizedMessage ?: "Some error occurred"
        )
    }

    /**
     * Executes a block of code within a try-catch block, handling exceptions
     * and returning a MongoDB response with an error in case of an exception.
     *
     * @param errorMessage The default error message if none is provided by the caught exception.
     * @param block The suspend function to execute within the try-catch block.
     * @return A [MongoResponse] containing either the successful result or an error.
     */
    suspend fun <Generic> mongoTryCatch(
        errorMessage: String? = null,
        block: suspend () -> MongoResponse<Generic>
    ): MongoResponse<Generic> = try {
        block()
    } catch (ex: Exception) {
        MongoResponse.Error(errorMessage ?: ex.localizedMessage ?: "Some error occurred")
    }
}


/*fun <Generic> handleMongoResponse(
       mongoResponse: MongoResponse<Generic>,
       successMessage: String,
       elseErrorMessage: String = "",
   ): Pair<Int, String> {
       return when (mongoResponse) {
           is MongoResponse.Error -> badMethodRequest(
               code = 404,
               errorMessage = mongoResponse.error ?: elseErrorMessage
           )

           is MongoResponse.Success -> Pair(
               200,
               Json.encodeToString(
                   JVMApiResponse.Success(
                       response = mongoResponse.data,
                       responseMessage = successMessage,
                       statusCode = 200
                   )
               )
           )

       }
   }*/