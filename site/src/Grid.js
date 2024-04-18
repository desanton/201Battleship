import React, { useState } from 'react';

// Component for a single cell in the grid
const Cell = ({ onClick, status }) => {
  let cellStyle = {
    width: '30px',
    height: '30px',
    border: '1px solid black',
    backgroundColor: status === 'empty' ? 'white' : status === 'ship' ? 'blue' : status === 'hit' ? 'red' : 'gray',
    cursor: status === 'empty' ? 'pointer' : 'default',
  };

  return <div style={cellStyle} onClick={onClick}></div>;
};

// Component for the grid
const Grid = ({ onClick }) => {
  const [grid, setGrid] = useState(Array.from({ length: 10 }, () => Array(10).fill('empty')));

  const handleCellClick = (rowIndex, colIndex) => {
    onClick(rowIndex, colIndex);
  };

  return (
    <div>
      {grid.map((row, rowIndex) => (
        <div key={rowIndex} style={{ display: 'flex' }}>
          {row.map((cell, colIndex) => (
            <Cell key={colIndex} status={cell} onClick={() => handleCellClick(rowIndex, colIndex)} />
          ))}
        </div>
      ))}
    </div>
  );
};


// Main App component
const App = () => {
  const [ships, setShips] = useState([]);
  const [hits, setHits] = useState([]);
  const [misses, setMisses] = useState([]);

  const handleCellClick = (rowIndex, colIndex) => {
    // Check if the cell has already been clicked
    if (hits.some(hit => hit[0] === rowIndex && hit[1] === colIndex) || misses.some(miss => miss[0] === rowIndex && miss[1] === colIndex)) {
      return;
    }

    // Check if the clicked cell contains a ship
    const isShip = ships.some(ship => ship[0] === rowIndex && ship[1] === colIndex);

    if (isShip) {
      // Update hits
      setHits([...hits, [rowIndex, colIndex]]);
    } else {
      // Update misses
      setMisses([...misses, [rowIndex, colIndex]]);
    }
  };

  return (
    <div>
      <Grid onClick={handleCellClick} />
    </div>
  );
};

export default App;
