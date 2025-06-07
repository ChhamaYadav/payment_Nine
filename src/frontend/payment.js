document.addEventListener("DOMContentLoaded", () => {
    const toggles = document.querySelectorAll('.dropdown-toggle');

    toggles.forEach(toggle => {
        toggle.addEventListener('click', () => {
            const targetId = toggle.dataset.target;
            const targetSection = document.getElementById(targetId);

            // Close all sections
            document.querySelectorAll('.accordion-section').forEach(section => {
                if (section !== targetSection) {
                    section.classList.remove('active');
                }
            });

            // Toggle current section
            targetSection.classList.toggle('active');
        });
    });

    // Open first accordion section by default
    const firstSection = document.querySelector('.accordion-section');
    if (firstSection) {
        firstSection.classList.add('active');
    }

    // Contact Info validation
    document.getElementById('contact').addEventListener('blur', validateContactInfo);

    // Apply coupon button
    document.getElementById('apply-btn').addEventListener('click', applyCoupon);

    // Fetch cart summary
    fetchCartSummary();

    // Tick display on input blur
    document.querySelectorAll('input, textarea').forEach(input => {
        input.addEventListener('blur', () => {
            if (input.value.trim() !== '') {
                const tickId = input.id + 'Tick';
                const tick = document.getElementById(tickId);
                if (tick) tick.style.display = 'inline';
            }
        });
    });
});

function validateContactInfo() {
    const contactValue = document.getElementById('contact').value.trim();
        const tick = document.getElementById('contactTick');

        const isEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(contactValue);
        const isPhone = /^[0-9]{10}$/.test(contactValue);  // Adjust as per country format

        if (isEmail || isPhone) {
            tick.style.display = 'inline';
            document.getElementById('contactSection').classList.add('completed');
        } else {
            tick.style.display = 'none';
            document.getElementById('contactSection').classList.remove('completed');
        }
}

function addNewAddress() {
    const addressGroup = document.querySelector('.address-group');

    const newTextarea = document.createElement('textarea');
    newTextarea.placeholder = "New Address (e.g., Office)";
    newTextarea.required = true;

    addressGroup.appendChild(newTextarea);
}

// Store userId and totalAmount in JS variables (you already do this)
const userId = 12;
let totalAmount = 0;

async function fetchCartSummary() {
    try {
        const response = await fetch(`http://localhost:9091/payment/cartsummary/${userId}`);
        if (!response.ok) throw new Error('Failed to load cart summary');

        const data = await response.json();
        totalAmount = data.totalAmount;

        document.getElementById('itemsTotal').innerText = `₹${totalAmount.toFixed(2)}`;
        document.getElementById('total').innerText = totalAmount.toFixed(2);

        const itemList = document.getElementById('itemList');
        itemList.innerHTML = '';

        data.items.forEach(item => {
            const itemEl = document.createElement('div');
            itemEl.className = 'order-item';

            const imgSrc = item.imageURL ? item.imageURL : 'https://placehold.co/60x60';
            itemEl.innerHTML = `
                <img src="${imgSrc}" alt="${item.productName}" class="item-img">
                <div class="item-details">
                    <p class="item-name">${item.productName}</p>
                    <p class="item-price">₹${item.total.toFixed(2)} (${item.quantity} × ₹${item.price.toFixed(2)})</p>
                </div>
            `;

            itemList.appendChild(itemEl);
        });

    } catch (error) {
        console.error('Error fetching cart summary:', error.message);
    }
}

async function applyCoupon() {
    const couponCode = document.getElementById("coupon").value;

    if (!couponCode) {
        alert("Please enter a coupon code");
        return;
    }

    const requestPayload = {
        userId,
        couponCode,
        totalAmount
    };

    try {
        const response = await fetch("http://localhost:9091/payment/coupon/apply", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestPayload)
        });

        if (!response.ok) throw new Error("Coupon apply request failed");

        const data = await response.json();
        alert(`Message: ${data.message}\nDiscounted Amount: ₹${data.discountedAmount.toFixed(2)}`);

    } catch (error) {
        console.error("Error applying coupon:", error);
        alert("Failed to apply coupon.");
    }
}
// Attach the event listener AFTER DOM is loaded
document.addEventListener("DOMContentLoaded", () => {
  document.getElementById('apply-btn').addEventListener('click', applyCoupon);
  fetchCartSummary();
});


document.addEventListener("DOMContentLoaded", () => {
    const paymentRadios = document.querySelectorAll('input[name="payment"]');
    const cardDetails = document.getElementById("credit-card-details");
    const upiDetails = document.getElementById("upi-details");

    function togglePaymentDetails() {
        const selected = document.querySelector('input[name="payment"]:checked').id;
        cardDetails.style.display = selected === "credit-card" ? "block" : "none";
        upiDetails.style.display = selected === "upi" ? "block" : "none";
    }

    paymentRadios.forEach(radio => {
        radio.addEventListener("change", togglePaymentDetails);
    });

    togglePaymentDetails(); // Run on load
});

document.addEventListener("DOMContentLoaded",()=>{
    fetchCartSummary()
});

//function fetchOrderSummary() { console.log('Hitting fetchCartSummary()');
// const userId=12;
//  fetch('http://localhost:9091/payment/cartsummary/${userId}')  // Replace with your API endpoint
//    .then(response => {
//      if (!response.ok) {
//        throw new Error("Network response was not ok");
//      }
//      return response.json(); // Assuming API returns JSON
//    })
//    .then(data => {
//      displaySummary(data);
//    })
//    .catch(error => {
//      console.error("There was a problem with the fetch:", error);
//      document.getElementById('summary-content').innerText = "Failed to load summary.";
//    });
//}
//
//function displaySummary(data) {
//  const summaryContainer = document.getElementById("summary-content");
//
//  // Clear previous data
//  summaryContainer.innerHTML = "";
//
//  // Example rendering logic
//  const itemsHTML = data.items.map(item => `
//    <div class="summary-item">
//      <span>${item.name}</span>
//      <span>Qty: ${item.quantity}</span>
//      <span>₹${item.price}</span>
//    </div>
//  `).join("");
//
//  const totalHTML = `<div class="summary-total"><strong>Total:</strong> ₹${data.total}</div>`;
//
//  summaryContainer.innerHTML = itemsHTML + totalHTML;
//}

//async function applyCoupon() {
//console.log("Coupon applied");
//
//    const requestPayload = {
//        userId: parseInt(document.getElementById("userId").value),
//        couponCode: document.getElementById("coupon").value,
//        totalAmount: parseFloat(document.getElementById("totalAmount").value)
//    };
//
//    try {
//        const response = await fetch("http://localhost:9091/payment/coupon/apply", {
//            method: "POST",
//            headers: {
//                "Content-Type": "application/json"
//            },
//            body: JSON.stringify(requestPayload)
//        });
//
//        const data = await response.json();
//        console.log("Coupon Response:", data);
//
//        // Show result on UI
//        alert(`Message: ${data.message}\nDiscounted Amount: ${data.discountedAmount}`);
//    } catch (error) {
//        console.error("Error applying coupon:", error);
//        alert("Failed to apply coupon.");
//    }
//}


