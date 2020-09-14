import React, { Component } from 'react';
//import quizQuestions from './api/quizQuestions';
import academicQuestions from './api/academicQuestions';
import Quiz from './components/Quiz';
import Result from './components/Result';
import logo from './svg/logo.svg';
import { PieChart } from 'react-minimal-pie-chart';
import './App.css';
import './index.css';

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

class App extends Component {

  constructor(props) {
    super(props);

    this.state = {
      counter: 0,
      questionId: 1,
      question: '',
      answerOptions: [],
      answer: '',
      answersCount: {},
      result: ''
    };

    this.handleAnswerSelected = this.handleAnswerSelected.bind(this);
  }

  componentDidMount() {
	  console.log(academicQuestions);
    /*const shuffledAnswerOptions = academicQuestions.map(question =>
      this.shuffleArray(question.options)
    );*/
    this.setState({
      question: academicQuestions[0].question,
      answerOptions: academicQuestions[0].options;//shuffledAnswerOptions[0]
    });
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
    this.setUserAnswer(event.currentTarget.value);

    if (this.state.questionId < academicQuestions.length) {
      setTimeout(() => this.setNextQuestion(), 300);
    } else {
      setTimeout(() => this.setResults(this.getResults()), 300);
    }
  }

  setUserAnswer(answer) {
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
      question: academicQuestions[counter].question,
      answerOptions: academicQuestions[counter].options,
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

  renderQuiz() {
    return (
      <Quiz
        answer={this.state.answer}
        answerOptions={this.state.answerOptions}
        questionId={this.state.questionId}
        question={this.state.question}
        questionTotal={academicQuestions.length}
        onAnswerSelected={this.handleAnswerSelected}
      />
    );
  }

  renderResult() {
    return <Result quizResult={this.state.result} />;
  }

  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
        </div>
		<div>
		    <PieChart radius="30" 
			   style={{ height: '700px' }} 
			   label={({ dataEntry }) => dataEntry.title}
			   labelStyle={{
					  ...defaultLabelStyle,
					}} 
			  data={dimentionMock}
			  onClick={(event, index) => {
					console.log('CLICK', { event, index });
					//setSelected(index === selected ? undefined : index);
				  }}
				  onMouseOver={(_, index) => {
					  console.log('On Mouse Over', {index });
				  }}
				  onMouseOut={() => {
					  console.log('On Mouse Out');
				  }}
			  />
		  </div>
		<div>
		<h2>React Quiz</h2>
        {this.state.result ? this.renderResult() : this.renderQuiz()}
        </div>
	  </div>
    );
  }
}

export default App;
