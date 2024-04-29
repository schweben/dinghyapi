import React, { useState } from 'react';

import './App.css';

import SearchForm from './components/SearchForm';
import Results from './components/Results';

function App() {
  const [dinghies, setDinghies] = useState();

  function search(name, manufacturer, crew, symmetric, asymmetric, trapeze) {
    // TODO Make this more concise
    let queryString = "/v1/dinghies?";
    if (name) {
      queryString += "name=" + name;
    }
    if (manufacturer) {
      queryString += "&manufacturer=" + manufacturer;
    }
    if (crew) {
      queryString += "&crew=" + crew;
    }
    if (symmetric) {
      queryString += "&symmetric=" + symmetric;
    }
    if (asymmetric) {
      queryString += "&asymmetric=" + asymmetric;
    }
    if (trapeze) {
      queryString += "&trapeze=" + trapeze;
    }
    console.log('Searching for ' + queryString);
    fetch(queryString)
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
      <main>
          <SearchForm search={search}/>
          <Results dinghies={dinghies}/>
      </main>
    </div>
  );
}

export default App;
