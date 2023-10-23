function detectDeviceType() {
    // Check the user agent string
    const userAgent = navigator.userAgent;

    // Check the screen width
    const screenWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;

    // Define the breakpoints for different device types
    const mobileBreakpoint = 768; // Adjust as needed
    const tabletBreakpoint = 992; // Adjust as needed

    if (screenWidth < mobileBreakpoint || /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(userAgent)) {
        return 'Mobile';
    } else if (screenWidth < tabletBreakpoint) {
        return 'Tablet';
    } else {
        return 'Desktop';
    }
}

// Example usage:
const deviceType = detectDeviceType();
console.log('Device type: ' + deviceType);
