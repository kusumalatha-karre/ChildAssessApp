import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
}));

export default function ButtonAppBar(props) {
  const classes = useStyles();
  const isLoggedIn = props.isLoggedIn;
  if(isLoggedIn) {
	return (
	 <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
            Child Assessment Platform
          </Typography>
          <Button color="inherit" onClick= { props.signOutBtnHandler }>Log out</Button>		  
        </Toolbar>
      </AppBar>
     </div>
    ); 
  } else {
	return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
            Child Assessment Platform
          </Typography>
          <Button color="inherit" onClick= { props.signInBtnHandler }>Sign In</Button>
		  <Button color="inherit" onClick= { props.signUpBtnHandler }>Sign Up</Button>
        </Toolbar>
      </AppBar>
    </div>
  );  
 }  
}