document.getElementById('loginForm').addEventListener('submit', function(event) {
  event.preventDefault();

  const user = document.getElementById('user').value;
  const password = document.getElementById('password').value;

  if (user && password) {
    alert('Login successful! This is a demo page.');
  } else {
    alert('Please fill in all fields');
  }
});
