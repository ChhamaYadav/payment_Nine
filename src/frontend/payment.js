
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll('.dropdown-toggle').forEach(toggle => {
        toggle.addEventListener('click', () => {
            const sectionId = toggle.dataset.target;
            const section = document.getElementById(sectionId);
            section.classList.toggle('collapsed');
            toggle.classList.toggle('rotated');
        });
    });
});

document.querySelectorAll('.dropdown-toggle').forEach(toggle => {
    toggle.addEventListener('click', (e) => {
        const sectionId = toggle.dataset.target;
        const section = document.getElementById(sectionId);
        section.classList.toggle('collapsed');
        toggle.classList.toggle('rotated');
    });
});

// Simple validation simulation
document.querySelectorAll('input, textarea').forEach(input => {
  input.addEventListener('blur', () => {
    if (input.value.trim() !== '') {
      const tickId = input.id + 'Tick';
      const tick = document.getElementById(tickId);
      if (tick) tick.style.display = 'inline';
    }
  });
});

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


function validateContactInfo() {
    const number = document.getElementById('contactNumber').value.trim();
    const email = document.getElementById('contactEmail').value.trim();
    const tick = document.getElementById('contactTick');

    if (number && email.includes('@')) {
        tick.style.display = 'inline'; // ✅ show green tick
        document.getElementById('contactSection').classList.add('completed');
    } else {
        tick.style.display = 'none'; // hide tick
        document.getElementById('contactSection').classList.remove('completed');
    }
}

// Trigger validation on blur or submit
document.getElementById('contactNumber').addEventListener('blur', validateContactInfo);
document.getElementById('contactEmail').addEventListener('blur', validateContactInfo);


function addNewAddress() {
    const addressGroup = document.querySelector('.address-group');

    const newTextarea = document.createElement('textarea');
    newTextarea.placeholder = "New Address (e.g., Office)";
    newTextarea.required = true;

    addressGroup.appendChild(newTextarea);
}

//document.querySelectorAll('.checkout-section h2').forEach(header => {
//    header.addEventListener('click', () => {
//        const section = header.parentElement;
//        section.classList.toggle('collapsed'); // Add CSS for .collapsed
//    });
//});




