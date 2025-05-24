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
