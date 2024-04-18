import React, { useState } from 'react';
import Grid from './Grid';
import './GameBoard.css';
import GamePlayPage from './GamePlayPage'; // Import the GamePlayPage component

const GameBoardPage = () => {
  // State variables for wins and losses
  const [wins, setWins] = useState(0);
  const [losses, setLosses] = useState(0);
  // State variable to store ship coordinates
  const [shipCoordinates, setShipCoordinates] = useState({
    aircraftCarrier: '',
    battleship: '',
    cruiser: '',
    submarine: '',
    destroyer: ''
  });
  const [gameStarted, setGameStarted] = useState(false);

  const handleShipPlacement = (shipType, coordinates) => {
    // Add your logic here to handle ship placement
    console.log('Placing ship:', shipType, 'at coordinates:', coordinates);
    // Update the ship coordinates in the state
    setShipCoordinates({ ...shipCoordinates, [shipType]: coordinates });
  };

  const handleStartGame = () => {
    // Add your logic here to start the game
    console.log('Starting the game...');
    // Set gameStarted to true
    setGameStarted(true);
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
      {!gameStarted ? ( // Render initial setup if the game hasn't started
        <div className="initial-setup">
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
            <button onClick={handleStartGame}>Start Game</button>
        </div>
      ) : ( // Render GamePlayPage if the game has started
        <GamePlayPage />
      )}
    </div>
  );
};

export default GameBoardPage;
