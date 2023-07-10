import logo from "./logo.svg";
import "./App.css";
import Login from "./componets/Login";
import { AuthProvider } from "./componets/context/AuthContext";

function App() {
  return (
    <div className="App">
      <AuthProvider>
            <Login />
      </AuthProvider>
    </div>
  );
}

export default App;
