import React, { useState } from 'react';
import Grid from './Grid'; 
import './GameBoard.css';

const GameBoardPage = () => {
  
  const [wins, setWins] = useState(0);
  const [losses, setLosses] = useState(0);

  const [shipCoordinates, setShipCoordinates] = useState({
    aircraftCarrier: '',
    battleship: '',
    cruiser: '',
    submarine: '',
    destroyer: ''
  });

  const handleShipPlacement = (shipType, coordinates) => {
    // Add logic to handle ship placement
    console.log('Placing ship:', shipType, 'at coordinates:', coordinates);
    // Update ship coordinates in the state
    setShipCoordinates({ ...shipCoordinates, [shipType]: coordinates });
  };

  const handleStartGame = () => {
    // Add logic to start the game
    console.log('Starting the game...');
  };

  return (
    <div className="game-board-page">
      <div className="header">
        <h1>Battleship</h1>
      </div>
      <div className="stats">
        <div>Wins: {wins}</div>
        <div>Losses: {losses}</div>
      </div>    
      <div className="game-board-container">
        <div className="player-board">
          <h2>Player's Board</h2>
          <Grid />
        </div>
        <div className="opponent-board">
          <h2>Opponent's Board</h2>
          <Grid />
        </div>
      </div>
      <div className="ship-inputs">
            <h3>Ship Placement:</h3>
            <div>
              <label>Aircraft Carrier:</label>
              <input type="text" placeholder="Start X" className="input-small" />
              <input type="text" placeholder="Start Y" className="input-small" />
              <input type="text" placeholder="End X" className="input-small" />
              <input type="text" placeholder="End Y" className="input-small" />
            </div>
            <div>
              <label>Battleship:</label>
              <input type="text" placeholder="Start X" className="input-small" />
              <input type="text" placeholder="Start Y" className="input-small" />
              <input type="text" placeholder="End X" className="input-small" />
              <input type="text" placeholder="End Y" className="input-small" />
            </div>
            <div>
              <label>Cruiser:</label>
              <input type="text" placeholder="Start X" className="input-small" />
              <input type="text" placeholder="Start Y" className="input-small" />
              <input type="text" placeholder="End X" className="input-small" />
              <input type="text" placeholder="End Y" className="input-small" />
            </div>
            <div>
              <label>Submarine:</label>
              <input type="text" placeholder="Start X" className="input-small" />
              <input type="text" placeholder="Start Y" className="input-small" />
              <input type="text" placeholder="End X" className="input-small" />
              <input type="text" placeholder="End Y" className="input-small" />
            </div>
            <div>
              <label>Destroyer:</label>
              <input type="text" placeholder="Start X" className="input-small" />
              <input type="text" placeholder="Start Y" className="input-small" />
              <input type="text" placeholder="End X" className="input-small" />
              <input type="text" placeholder="End Y" className="input-small" />
            </div>
            <button onClick={handleStartGame}>Start Game</button>
          </div>
    </div>
  );
};

export default GameBoardPage;
