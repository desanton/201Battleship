import React, { useState } from 'react';
import Grid from './Grid';
import './GameBoard.css';

const GamePlayPage = ({ playerBoard, opponentBoard }) => {
  const [targetX, setTargetX] = useState('');
  const [targetY, setTargetY] = useState('');

  const handleTargetSubmit = () => {
    // Add logic to handle target submission
    console.log('Target coordinates submitted:', targetX, targetY);
  };

  return (
    <div className="game-play-page">
      <div className="game-board-container">
        <div className="player-board">
          <h2>Player's Board</h2>
          <Grid board={playerBoard} />
        </div>
        <div className="opponent-board">
          <h2>Opponent's Board</h2>
          <Grid board={opponentBoard} />
        </div>
      </div>
      <div className="target-inputs">
        <h3>Select Target Coordinates:</h3>
        <div>
          <input
            type="text"
            placeholder="Target X"
            value={targetX}
            onChange={(e) => setTargetX(e.target.value)}
          />
          <input
            type="text"
            placeholder="Target Y"
            value={targetY}
            onChange={(e) => setTargetY(e.target.value)}
          />
        </div>
        <button onClick={handleTargetSubmit}>Submit Target</button>
      </div>
    </div>
  );
};

export default GamePlayPage;
