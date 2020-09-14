import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import './App.css';
import SignUp from './components/SignUp';
import DashBoard from './components/DashBoard';
// styles
import "./assets/css/bootstrap.min.css";
import "./assets/css/now-ui-kit.css";
import "./assets/demo/demo.css";
import TransparentFooter from "./components/Footers/TransparentFooter.js";
//import LoginPage from "./views/examples/LoginPage.js";
import * as serviceWorker from './serviceWorker';

//ReactDOM.render(<App />, document.getElementById('root'));
ReactDOM.render(<DashBoard/>, document.getElementById('root'));

serviceWorker.unregister();
