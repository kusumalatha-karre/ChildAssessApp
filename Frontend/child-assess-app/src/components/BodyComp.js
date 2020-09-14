import React, { Component } from 'react';
import logo from '../svg/logo.svg';
import { PieChart } from 'react-minimal-pie-chart';
import '../App.css';
import '../index.css';
import {getQuestions} from '../api/service/questionService.js';
import Quiz from './Quiz';
import Result from './Result';
import { makeStyles } from '@material-ui/core/styles';
//import Pagination from '@material-ui/lab/Pagination';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      marginTop: theme.spacing(2),
    },
  },
}));

const defaultLabelStyle = {
	fontSize: '2px',
	fontFamily: 'sans-serif',
  };

const dimentionMock = [
  { title: 'Speech', value: 20, color: '#E38627' },
  { title: 'Special Education', value: 20, color: '#C13C37' },
  { title: 'Cognitive therapy', value: 20, color: '#6A2135' },
  { title: 'Food', value: 20, color: '#6A2135' },
  { title: 'Dozee', value: 20, color: '#6A2135' },
];

class BodyComp extends Component {

  constructor(props) {
    super(props);
	this.state = {
		questions : [],
		selectedIndex:1,
		selectedDimention: 'Speech',
		counter: 0,
        questionId: 1,
        question: '',
        options: [],
        answer: '',
        answersCount: {},
        result: ''
	}
	this.handleAnswerSelected=this.handleAnswerSelected.bind(this);
  }
  
  componentDidMount() {
	/*const shuffledAnswerOptions = academicQuestions.map(question =>
      this.shuffleArray(question.options)
    );*/
	/*this.setState({
      question: academicQuestions[0].question,
      options: academicQuestions[0].options,	  
	  questionId: academicQuestions[0].questionNo      
    });*/
  }
  
  shuffleArray(array) {
    var currentIndex = array.length,
      temporaryValue,
      randomIndex;

    // While there remain elements to shuffle...
    while (0 !== currentIndex) {
      // Pick a remaining element...
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex -= 1;

      // And swap it with the current element.
      temporaryValue = array[currentIndex];
      array[currentIndex] = array[randomIndex];
      array[randomIndex] = temporaryValue;
    }

    return array;
  }

  handleAnswerSelected(event) {
	let selAnswer = event.currentTarget.value;
	console.log('handleAnswerSelected called with ::'+selAnswer);
    this.setUserAnswer(selAnswer);
	//this.setState({ counter: this.state.counter + 1 })
    if (this.state.questionId < this.state.questions.length) {
      setTimeout(() => this.setNextQuestion(), 300);
    } else {
      setTimeout(() => this.setResults(this.getResults()), 300);
    }
  }

  setUserAnswer(answer) {
	console.log('Inside setUserAnswer' + answer);
    this.setState((state, props) => ({
      answersCount: {
        ...state.answersCount,
        [answer]: (state.answersCount[answer] || 0) + 1
      },
      answer: answer
    }));
  }

  setNextQuestion() {
    const counter = this.state.counter + 1;
    const questionId = this.state.questionId + 1;

    this.setState({
      counter: counter,
      questionId: questionId,
      question: this.state.questions[counter].question,
      answerOptions: this.state.questions[counter].options,
      answer: ''
    });
  }

  getResults() {
    const answersCount = this.state.answersCount;
    const answersCountKeys = Object.keys(answersCount);
    const answersCountValues = answersCountKeys.map(key => answersCount[key]);
    const maxAnswerCount = Math.max.apply(null, answersCountValues);

    return answersCountKeys.filter(key => answersCount[key] === maxAnswerCount);
  }

  setResults(result) {
    if (result.length === 1) {
      this.setState({ result: result[0] });
    } else {
      this.setState({ result: 'Undetermined' });
    }
  }

  
  render() {
    return (
	  <div>	
		<h4> Welcome Kusumalatha!! Please click on each dimension below and answer the questionaire accordingly : </h4>	  		
		  <PieChart radius="50" 
			   style={{ height: '300px', align:'right' }} 
			   label={({ dataEntry }) => dataEntry.title}
			   labelStyle={{
					  ...defaultLabelStyle,
					}} 
			  data={dimentionMock}
			  onClick={(event, index) => {
					console.log('CLICK', { event, index });
					this.setState({selectedIndex:index, selectedDimention: dimentionMock[index].title});
				  }}
				  /*onMouseOver={(_, index) => {
					  console.log('On Mouse Over', {index });
				  }}
				  onMouseOut={() => {
					  console.log('On Mouse Out');
				  }}*/
			  />
		
	    <div>
		  <center> 
		  <h3>{this.state.selectedDimention}</h3>
		  {this.renderQuiz(this.state.selectedDimention)}	
		  </center>		  
	    </div>
	  </div>
    );
  } 
  renderQuiz(dimension) {
	console.log('Called renderQuiz :: '+dimension);
	let questionArr = getQuestions(dimension);
	if(typeof(questionArr)!=="undefined" && questionArr.length>0) {
	  console.log('After Service call :: ' + questionArr);
	  //this.setState({questions:questionArr});
		console.log('render quiz state :: '+questionArr);
			return (
			<div>
			<Quiz
				answer={questionArr[this.state.counter].answer}
				options={questionArr[this.state.counter].options}
				questionId={questionArr[this.state.counter].questionNo}
				question={questionArr[this.state.counter].question}
				questionTotal={questionArr.length} 
				handleAnswerSelected={this.handleAnswerSelected}
			  />			  
			  </div>			  
			  )	
	} else {
		return ;
	}			
  }
}
export default BodyComp;
