const React = require('react');
const { useState } = require('react');
function LoginPage() {
    return (
        <div>
            <nav className="navbar">
                <div className="navbar-container">
                    <h1>Battleship Game</h1>
                </div>
                <div className="navbar-collapse">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <a className="nav-link" href="login.html">Login</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="register.html">Register</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <main>
                <div className="row">
                    <div className="centered-container"> 
                        <div className="p-4" id="loginForm">
                            <h2>Login</h2>
                            <p>Username:</p>
                            <form id="usernameForm">
                                <input type="text" name="loginUsername" className="searchBox" id="loginUsername" />
                                <p>Password:</p>
                                <input type="password" name="loginPassword" className="searchBox" id="loginPassword" />
                                <p></p>
                                <button type="submit" className="btn btn-block">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}

export default App;
