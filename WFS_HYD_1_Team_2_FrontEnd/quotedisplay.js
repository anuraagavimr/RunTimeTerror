document.addEventListener("DOMContentLoaded", function() {
    
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

   
    const orderId = urlParams.get("orderId");
    const date = urlParams.get("date");
    const shippingCost = urlParams.get("shippingCost");
    const totalCost = urlParams.get("totalCost");
    

    
    const invoiceDataContainer = document.getElementById("quoteData");

    if (invoiceDataContainer) {
        invoiceDataContainer.innerHTML = `
            <p><strong>Order Id:</strong> ${orderId}</p>
            <p><strong>Date:</strong> ${date}</p>
            <p><strong>Shipping Cost:</strong> ${shippingCost}</p>
            <p><strong>Total Cost:</strong> ${totalCost}</p>
           
        `;
    }
});