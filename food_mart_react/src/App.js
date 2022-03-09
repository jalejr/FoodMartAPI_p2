import './App.css';
import LoginMain from './components/login';
import GetAllRoles from './components/Roles/getAll';

function App() {
  return (
    <div className="App">
      <header className='App-header'>
      <LoginMain name = "Awaab"/>
      <GetAllRoles/>
        
      </header>
    </div>
  );
}

export default App;
