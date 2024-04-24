import './App.css'
import Body from './layouts/Body/Body';
import LeftPanel from './layouts/LeftPanel/LeftPanel';
import Header from "./components/Header/Header.jsx";

function App() {
  return (
    <>
        <div className='app'>
            <LeftPanel>
                <Header/>
            </LeftPanel>
            <Body>
            </Body>
        </div>
    </>
  )
}

export default App
