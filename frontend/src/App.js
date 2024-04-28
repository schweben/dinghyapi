import React, { useEffect, useState } from 'react';

import logo from './logo.svg';
import './App.css';

function App() {
  const [dinghies, setDinghies] = useState();

  useEffect(() => {
    fetch('/v1/dinghies/name/rs')
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setDinghies(() => data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div className="App-intro">
          <h2>Dinghies</h2>
          { dinghies.map((dinghy) =>
            <div key="{dinghy.id}">
              <h3>{dinghy.name}</h3>
            </div>
          )}
        </div>
      </header>
    </div>
  );
}

export default App;
