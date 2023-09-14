
document.addEventListener("DOMContentLoaded", function() {
   
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

   
    const orderId = urlParams.get("orderId");
    const customerName = urlParams.get("customerName");
    const orderDate = urlParams.get("orderDate");
    const orderValue = urlParams.get("orderValue");
    const customerCity = urlParams.get("customerCity");

    
    const invoiceDataContainer = document.getElementById("invoiceData");

    if (invoiceDataContainer) {
        invoiceDataContainer.innerHTML = `
            <p><strong>Order Id:</strong> ${orderId}</p>
            <p><strong>Customer Name:</strong> ${customerName}</p>
            <p><strong>Order Date:</strong> ${orderDate}</p>
            <p><strong>Order Value:</strong> ${orderValue}</p>
            <p><strong>Customer City:</strong> ${customerCity}</p>
        `;
    }
});
