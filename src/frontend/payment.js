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
    document.getElementById('contactNumber').addEventListener('blur', validateContactInfo);
    document.getElementById('contactEmail').addEventListener('blur', validateContactInfo);

    // Apply coupon button
    document.getElementById('applyCouponBtn').addEventListener('click', applyCoupon);

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
    const number = document.getElementById('contactNumber').value.trim();
    const email = document.getElementById('contactEmail').value.trim();
    const tick = document.getElementById('contactTick');

    if (number && email.includes('@')) {
        tick.style.display = 'inline';
        document.getElementById('contactSection').classList.add('completed');
    } else {
        tick.style.display = 'none';
        document.getElementById('contactSection').classList.remove('completed');
    }
}

function applyCoupon() {
    const code = document.getElementById('coupon').value.trim();
    const totalEl = document.getElementById('total');
    const discountEl = document.getElementById('discount');
    let total = 2000;

    if (code === 'SAVE10') {
        total -= 200;
        discountEl.textContent = "Coupon applied: -₹200";
    } else {
        discountEl.textContent = "Invalid code";
    }

    totalEl.textContent = total;
}

function addNewAddress() {
    const addressGroup = document.querySelector('.address-group');

    const newTextarea = document.createElement('textarea');
    newTextarea.placeholder = "New Address (e.g., Office)";
    newTextarea.required = true;

    addressGroup.appendChild(newTextarea);
}

const userId = 12;

async function fetchCartSummary() {
    try {
        const response = await fetch(`http://localhost:9091/payment/cartsummary/${userId}`);
        if (!response.ok) throw new Error('Failed to load cart summary');

        const data = await response.json();
        console.log('Cart Data:', data);

        document.getElementById('itemsTotal').innerText = `₹${data.totalAmount.toFixed(2)}`;
        document.getElementById('total').innerText = data.totalAmount.toFixed(2);

        const itemList = document.getElementById('itemList');
        itemList.innerHTML = '';
        data.items.forEach(item => {
            const itemEl = document.createElement('div');
            itemEl.className = 'order-item';

            // Use placeholder if imageURL is null
            const imgSrc = item.imageURL ? item.imageURL : 'https://via.placeholder.com/60';

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


