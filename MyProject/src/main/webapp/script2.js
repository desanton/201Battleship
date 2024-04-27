document.addEventListener("DOMContentLoaded", function() {
	
	//startGame();
	
	
    const boardSize = 10; // 10x10 board
    const playerBoard = document.getElementById('playerBoard');
    const opponentBoard = document.getElementById('opponentBoard');
    const coordinatesDisplay = document.getElementById('coordinatesDisplay');

    // Initialize the board states
    // Note: This array is now used for initial random ship placement but should also reflect changes from server responses.
    const opponentBoardState = new Array(boardSize * boardSize).fill(0); 
    
    
    // Randomly place ships for demo, but server will handle actual game logic in production
    for (let i = 0; i < 20; i++) {
        opponentBoardState[Math.floor(Math.random() * boardSize * boardSize)] = 1;
    }

    // Function to create boards
    function createBoard(boardElement, isOpponent, boardState) {
		
        for (let i = 0; i < boardSize * boardSize; i++) {
            let cell = document.createElement('div');
            cell.dataset.index = i; // Store index in dataset for easier access
            if (isOpponent) {
                cell.addEventListener('click', () => handleCellClick(cell, isOpponent, boardState));
            }
            boardElement.appendChild(cell);
        }
    }

    // Handle cell click function, now with server interaction
    function handleCellClick(cell, isOpponent, boardState) {
        if (!isOpponent) return; // Only interact with opponent's board

        const index = parseInt(cell.dataset.index);
        const x = index % boardSize;
        const y = Math.floor(index / boardSize);
        coordinatesDisplay.textContent = `Clicked coordinates: (${x}, ${y})`;

        // Send data to server
        /*fetch('/game', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ x: x, y: y })
        })
        .then(response => response.json())
        .then(data => {
            console.log('Server response:', data);
            updateCellBasedOnServerResponse(cell, data);
            boardState[index] = data; // Update local board state based on server response
        })
        .catch(error => console.error('Error:', error));*/
        
        
        let params = new URLSearchParams();
        params.append('x', x);
        params.append('y', y);

        // Send data to server
        fetch('/MyProject/CoordinateServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params
        })
        .then(response => response.text())
        .then(data => {
            console.log('Server response:', data);
            updateCellBasedOnServerResponse(cell, data);
            boardState[index] = data; // Update local board state based on server response
            coordinatesDisplay.textContent = `Clicked coordinates: (${x}, ${y}) - Server state: ${data}`;
            /*if (data === 5){
				gameOver();
			}*/
        })
        .catch(error => console.error('Error:', error));
    }

    function updateCellBasedOnServerResponse(cell, state) {
		
		const stateNumber = parseInt(state, 10);
		
		const index = parseInt(cell.dataset.index);
        const x = index % boardSize;
        const y = Math.floor(index / boardSize);
        
        const done = false;
		
        switch(stateNumber) {
            case 0:
                // Do nothing, as no visible effect is needed for empty and unclicked
                break;
            case 1:
                // Hit on a ship
                cell.classList.add('hit');
                break;
            case 2:
                // Entire ship sunk
                // cell.classList.add('hitall');
                // fetch all the coordinates by sending back the coordinate that is black first
                // cell.classList.add('hit');
                sinkShip(cell, x, y);
                break;
            case 3:
                // Missed attack
                cell.classList.add('miss');
                break;
            case 4:
                // Already attacked ship cell
                cell.classList.add('hit');
                break;
            case 5:
				sinkShip(cell, x, y);
				console.log('Is it ending');
				gameOver();
				done = true;
				break;
            default:
            	console.log("Unhandled state: ", stateNumber);
            	break;
        }
        console.log('IM HERE');

        
        if(!done){
			handleOpponent();
		}
    }
    
    /*function handleOpponent(){
		
		let params2 = new URLSearchParams();
        params2.append('x', x);
        params2.append('y', y);

        // Send data to server
        fetch('/MyProject/OpponentServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params2
        })
        .then(response => response.text())
        .then(data2 => {
            console.log('Server response:', data2);
            updateOpponentBoard(data2);
            // boardState[index] = data2;
            coordinatesDisplay.textContent = `Opponenet clicked coordinates: (${x}, ${y}) - Server state: ${data2}`;
        })
        .catch(error => console.error('Error:', error));
		
	}
    
    function updateOpponentBoard(state){
		
		const stateNumber = parseInt(state, 10);
		
		switch(stateNumber) {
            case 0:
                // Do nothing, as no visible effect is needed for empty and unclicked
                break;
            case 1:
                // Hit on a ship
                cell.classList.add('hit');
                break;
            case 2:
                // Entire ship sunk
                // cell.classList.add('hitall');
                // fetch all the coordinates by sending back the coordinate that is black first
                cell.classList.add('hit');
                // sinkShip(cell, x, y);
                break;
            case 3:
                // Missed attack
                cell.classList.add('miss');
                break;
            case 4:
                // Already attacked ship cell
                cell.classList.add('hit');
                break;
            default:
            	console.log("Unhandled state: ", stateNumber);
            	break;
        }
	}*/
	
	
	function handleOpponent() {
	    fetch('/MyProject/OpponentServlet', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/x-www-form-urlencoded'
	        },
	        body: ''
	    })
	    .then(response => response.text())
	    .then(data => {
	        const status = parseInt(data.charAt(0), 10);
	        const x = parseInt(data.charAt(1), 10); 
	        const y = parseInt(data.charAt(2), 10);
	        console.log('Server response:', data, 'x:', x, 'y:', y, 'status:', status);
	        updatePlayerBoard(x, y, status);
	    })
	    .catch(error => console.error('Error:', error));
	}
	
	function updatePlayerBoard(x, y, status) {
	    const index = y * boardSize + x;
	    const cell = playerBoard.children[index];
	    if (!cell) return;
	
	    switch(status) {
	        case 2: // sunken
	            cell.classList.add('hitall');
	            break;
	        case 3: // attacked empty
	            cell.classList.add('miss');
	            break;
	        case 4: // attacked occupied
	            cell.classList.add('hit');
	            break;
	        case 5:
	            cell.classList.add('hit');
	            gameOver();
	            break;
	        default:
	            console.log("Unhandled status: ", status);
	            break;
	    }
	}

	

    /*function sinkShip(cell, x, y) {
        let params = new URLSearchParams();
        params.append('x', x);
        params.append('y', y);

        fetch('/MyProject/ShipServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params
        })
        .then(response => response.text()) 
        .then(data => {
            console.log('Server response:', data);
            if (data.trim().length > 0) {
                updateCellsForSunkShip(data);
            }
        })
        .catch(error => console.error('Error:', error));
        
        
     function updateCellsForSunkShip(coordinatesData) {
            const coordinates = coordinatesData.split("\n"); 
            coordinates.forEach(coord => {
				
                if (coord.trim().length === 0) return;
                const parts = coord.split(",");
                const x = parseInt(parts[0].trim(), 10);
                const y = parseInt(parts[1].trim(), 10);
                const index = y * boardSize + x;
                const cell = document.querySelector(`div[data-index="${index}"]`);
                if (cell) {
                    cell.classList.add('hitall');
                }
            });
        }
    }*/
    
    
    
	function sinkShip(cell, x, y) {
	    let params = new URLSearchParams();
	    params.append('x', x);
	    params.append('y', y);
	
	    fetch('/MyProject/ShipServlet', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/x-www-form-urlencoded'
	        },
	        body: params
	    })
	    .then(response => response.text())
	    .then(data => {
	        console.log('Server response for sunk ship:', data);
	        if (data.trim().length > 0) {
	            updateCellsForSunkShip(data, opponentBoard); // Pass the correct board as an argument
	        }
	    })
	    .catch(error => console.error('Error:', error));
	}
	
	function updateCellsForSunkShip(coordinatesData, targetBoard) {
	    const coordinates = coordinatesData.split("\n");
	    coordinates.forEach(coord => {
	        if (coord.trim().length === 0) return;
	        const parts = coord.split(",");
	        const x = parseInt(parts[0].trim(), 10);
	        const y = parseInt(parts[1].trim(), 10);
	        const index = y * boardSize + x;
	        const cells = targetBoard.children; // Assuming children are directly cells, otherwise adjust selector
	        if (cells && cells[index]) {
	            cells[index].classList.add('hitall');
	            console.log(`Marking cell at (${x}, ${y}) as sunk.`);
	        }
	    });
	}
	
	
	function gameOver() {
		console.log('Is it getting in here');
	    /*const message = "PLAYER WON!";
	    coordinatesDisplay.textContent = message;
	    coordinatesDisplay.style.color = 'green';
	    coordinatesDisplay.style.fontWeight = 'bold';*/
	    
	    document.getElementById('hide').style.display = 'none';
	    document.getElementById('end').style.display = 'block';
	}
	
	
    // Creating both boards
    createBoard(playerBoard, false, []); // Player's board (no functionality for clicking)
    createBoard(opponentBoard, true, opponentBoardState); // Opponent's board
    
});

/*
function startGame() {
    fetch('/FinalProject/StartServlet')
        .then(response => response.text())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
}
*/