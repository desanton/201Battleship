document.addEventListener('DOMContentLoaded', function() {
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm-password');
    const usernameInput = document.getElementById('username');
    const emailInput = document.getElementById('email');
    const submitButton = document.querySelector('button[type="submit"]');
    const registrationForm = document.getElementById('registration-form');
    const loginForm = document.getElementById('login-form');

    function validatePassword() {
        if (passwordInput.value !== confirmPasswordInput.value) {
            confirmPasswordInput.setCustomValidity("Passwords don't match");
        } else {
            confirmPasswordInput.setCustomValidity('');
        }
    }

    passwordInput.addEventListener('change', validatePassword);
    confirmPasswordInput.addEventListener('keyup', validatePassword);

    registrationForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission
        if (passwordInput.value !== confirmPasswordInput.value) {
            alert("Passwords don't match. Please try again.");
        } else {
			
			const a = usernameInput.value;
			console.log(a);
			console.log(passwordInput.value);
			console.log(emailInput.value);
			
            const formData = `${usernameInput.value}\n${passwordInput.value}\n${emailInput.value}`;

            
            fetch('/MyProject/RegisterServlet', {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                // Handle successful response
                console.log(data);
            })
            .catch(error => {
                // Handle error
                console.error('Error:', error);
            });
        }
    });
    
    // Event listener for login form submission
    loginForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        // Submit login form
        const formData = new FormData(loginForm);
        fetch('/MyProject/LoginServlet', { // Change to your login servlet URL
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            // Handle successful response
            console.log(data);
        })
        .catch(error => {
            // Handle error
            console.error('Error:', error);
        });
    });
});


/*
function RegistrationPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    if(password !== confirmPassword) {
      alert('Passwords do not match!');
      return;
    }

    try {
      const response = await fetch('/FinalProject/LoginServlet', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();
      
      if (response.ok) {
        console.log('Registration successful', data);
        setUsername('');
        setPassword('');
        setConfirmPassword('');
      } else {
        console.error('Registration failed', data);
      }
    } catch (error) {
      console.error('There was an error registering the user:', error);
    }
  };
  
}
*/