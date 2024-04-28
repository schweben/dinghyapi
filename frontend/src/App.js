import React, { useEffect, useState } from 'react';

import logo from './logo.svg';
import './App.css';

import SearchForm from './components/SearchForm';
import Results from './components/Results';

function App() {
  const [dinghies, setDinghies] = useState();

  function search(name, manufacturer, crew, symmetric, asymmetric, trapeze) {
    console.log('Searching');
    fetch('/v1/dinghies/name/rs')
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setDinghies(() => data);
      })
      .catch((err) => {
        console.log(err.message);
      });
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <div className="App-intro">
          <h2>Dinghies</h2>
          <SearchForm search={search}/>
          <Results dinghies={dinghies}/>
        </div>
      </header>
    </div>
  );
}

export default App;
