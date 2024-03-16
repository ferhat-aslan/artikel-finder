import logo from "./logo.svg";
import "./App.css";
import { useState } from "react";

function App() {
  const [result, setResult] = useState("  ");
  const [loading, setLoading] = useState(false);
  function findArtikel() {
    if (loading) return;
    setLoading(true);
    setResult("Loading...");
    const value = document.getElementById("input-field").value;
    if (value.length < 2) return;
    /*  fetch("https://artikel-finder.onrender.com/artikel-finder", {
      method: "POST", // *GET, POST, PUT, DELETE, etc.
      mode: "cors", // no-cors, *cors, same-origin
      cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
      credentials: "same-origin", // include, *same-origin, omit
      headers: {
        "Content-Type": "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: "follow", // manual, *follow, error
      referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
      body: JSON.stringify({ word: "tür" }), // body data type must match "Content-Type" header
    }).then((e) => {
      console.log(e);
      setResult(e);
      setLoading(false);
    }); */
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const raw = JSON.stringify({
      word: value,
    });

    const requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch("http://localhost:8080/artikel-finder", requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);

        setResult(result);
        setLoading(false);
      })
      .catch((error) => {
        console.error(error);

        setResult("Error");
        setLoading(false);
      });
  }
  return (
    <div
      className="App"
      style={{ backgroundColor: "#282c34", backgroundImage: "url(/bg.jpg)" }}
    >
      <header className="App-header">
        <h1>Artikel Finder</h1>
        <div className="input-button-wrapper">
          <input
            id="input-field"
            type="text"
            placeholder="Type German Word..."
          />
          <button disabled={loading} onClick={() => findArtikel()}>
            Suche
          </button>
        </div>
        <p style={{ border: "1px solid orange", padding: "0px 15px" }}>
          {result}
        </p>
      </header>
    </div>
  );
}

export default App;
