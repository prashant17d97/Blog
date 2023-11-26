package com.prashant.blog.utils

import kotlinx.browser.document
import org.jetbrains.compose.web.dom.ElementBuilder
import org.w3c.dom.Element

class ElementBuilderImplementation<TElement : Element>(private val tagName: String) :
    ElementBuilder<TElement> {
    private val el: Element by lazy { document.createElement(tagName) }

    @Suppress("UNCHECKED_CAST")
    override fun create(): TElement = el.cloneNode() as TElement
}