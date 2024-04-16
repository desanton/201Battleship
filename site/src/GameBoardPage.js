import React, { useState } from 'react';
import Grid from './Grid'; // Import the Grid component
import './GameBoard.css';

const GameBoardPage = () => {
  // State variables for wins and losses
  const [wins, setWins] = useState(0);
  const [losses, setLosses] = useState(0);

  // Function to handle ship placement
  const handleShipPlacement = (shipType) => {
    // Add your logic here to handle ship placement
    console.log('Placing ship:', shipType);
  };

  return (
    <div className="game-board-page">
      <div className="stats">
        <div>Wins: {wins}</div>
        <div>Losses: {losses}</div>
      </div>
      <div className="player-board">
        <h2>Player's Board</h2>
        <Grid />
        <div className="ship-buttons">
          <button onClick={() => handleShipPlacement('Aircraft Carrier')}>Aircraft Carrier</button>
          <button onClick={() => handleShipPlacement('Battleship')}>Battleship</button>
          <button onClick={() => handleShipPlacement('Cruiser')}>Cruiser</button>
          <button onClick={() => handleShipPlacement('Submarine')}>Submarine</button>
          <button onClick={() => handleShipPlacement('Destroyer')}>Destroyer</button>
        </div>
      </div>
      <div className="opponent-board">
        <h2>Opponent's Board</h2>
        <Grid />
        <div className="ship-buttons">
          <button onClick={() => handleShipPlacement('Aircraft Carrier')}>Aircraft Carrier</button>
          <button onClick={() => handleShipPlacement('Battleship')}>Battleship</button>
          <button onClick={() => handleShipPlacement('Cruiser')}>Cruiser</button>
          <button onClick={() => handleShipPlacement('Submarine')}>Submarine</button>
          <button onClick={() => handleShipPlacement('Destroyer')}>Destroyer</button>
        </div>
      </div>
    </div>
  );
};

export default GameBoardPage;

