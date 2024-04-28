import React, { useEffect, useState } from 'react';

import logo from './logo.svg';
import './App.css';

import Search from './components/Search';
import Results from './components/Results';

function App() {
  const [dinghies, setDinghies] = useState();

  useEffect(() => {
    console.log('Getting data');
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
          <Search/>
          <Results dinghies={dinghies}/>
        </div>
      </header>
    </div>
  );
}

export default App;
