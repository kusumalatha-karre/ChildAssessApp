import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import IndexNavbar from './Navbars/IndexNavbar';
import SignIn from './SignIn';
import SignUp from './SignUp';
import Footer from './Footer';
import BodyComp from './BodyComp';
import ButtonAppBar from './ButtonAppBar';

class DashBoard extends React.Component {
  constructor(props) {
    super(props);
	this.state = {
		isLoggedIn: false,
		viewName: 'sign-in'
	}
	this.signUpBtnHandler = this.signUpBtnHandler.bind(this);
	this.signInBtnHandler = this.signInBtnHandler.bind(this);
	this.signInHandler = this.signInHandler.bind(this);
	this.signUpHandler = this.signUpHandler.bind(this);
	this.signOutBtnHandler = this.signOutBtnHandler.bind(this);
  }
  signOutBtnHandler() {
    this.setState({ isLoggedIn: false, viewName: 'sign-in' });    
  }
  
  signUpBtnHandler() {
	this.setState( { isLoggedIn: false, viewName: 'sign-up' });   
  }
  signInBtnHandler() {
	this.setState( { isLoggedIn: false, viewName: 'sign-in' });   
  }
  signInHandler() {
	  console.log('Ínside signInHandler'); 
	this.setState( { isLoggedIn: true, viewName: 'body' });  
  }
  signUpHandler() {
	this.setState( { isLoggedIn: false, viewName: 'sign-in' });  
  }
  
  loadComponent(isLoggedIn, viewName) {
	if(isLoggedIn) {
	  if (viewName === 'body')
	    return (<BodyComp/>);	
	} else {
		if (viewName === 'sign-up')
	      return (<SignUp signInHandler={this.signUpHandler} />);
	    else 
	      return (<SignIn signInHandler={this.signInHandler} />);
	}    	
  }
  
  render() {
  return (
    <div>
	 <div id="HeaderId">
		<ButtonAppBar 
			isLoggedIn={this.state.isLoggedIn} 
			signOutBtnHandler={this.signOutBtnHandler} 
			signUpBtnHandler={this.signUpBtnHandler} 
			signInBtnHandler={this.signInBtnHandler}/>
	 </div>
	 <div id="BodyId">
	 {this.loadComponent(this.state.isLoggedIn, this.state.viewName)}		
	 </div>
	 <div id="FooterId">
		<Footer/>
	 </div>
	</div>    
  );
  }
}

export default DashBoard;