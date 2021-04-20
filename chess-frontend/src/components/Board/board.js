import React from 'react'
import Row from './Row/row';
import './styles/board.css'
export default class Board extends React.Component {
  height = window.screen.height - 75
  constructor() {
    super();
    this.state = {
      board: [],
      userColor: null,
      yourPiece: null,
      thierPiece: null,
      turn: 0
    }
    this.componentDidMount = this.componentDidMount.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleThierPiece = this.handleThierPiece.bind(this);
    this.handleYourPiece = this.handleYourPiece.bind(this);
    this.getThierPiece = this.getThierPiece.bind(this);
    this.getYourPiece = this.getYourPiece.bind(this);
    this.getBoard = this.getBoard.bind(this);
  }

  componentDidMount() {
    if (this.props.getUserId() !== 0) {
      this.interval = setInterval(() => {
        fetch("http://localhost:5000/ChessAPI/getColor/" + this.props.getUserId(), {
          method: 'GET',
          headers: {
            "Content-type": "application/json"
          }
        })
          .then(res => res.text())
          .then(
            (result) => {
              this.setState({ userColor: result });
            }
          );
        fetch("http://localhost:5000/ChessAPI/getBoard/" + this.props.getUserId(), {
          method: 'GET',
          headers: {
            "Content-type": "application/json"
          }
        })
          .then(res => {
            if (res.status === 418) {
              console.log("Game over")
            }
            else {
              console.log("not over")
            }
            return res.json()
          })
          .then(
            (result) => {
              if (this.state.userColor === "Black") {
                console.log("black board getting flipped")
                for (let x = 0; x < 8; x++) {
                  for (let y = 0; y < 4; y++) {
                    /*
                    let type = result[x][7 - y].type;
                    let color = result[x][7 - y].color
                    result[x][7 - y].type = result[x][y].type
                    result[x][7 - y].color = result[x][y].color
                    result[x][y].type = type
                    result[x][y].color = color*/
                    let temp = result[x][7-y];
                    result[x][7-y] = result[x][y]
                    result[x][y] = temp;
                  }
                }
              }
              this.setState({ board: result });
            },
            (error) => {
              console.log("error in fetching board");
            }
          )
        fetch("http://localhost:5000/ChessAPI/getTurn/" + this.props.getUserId(), {
          method: 'GET',
          headers: {
            "Content-type": "application/json"
          }
        })
          .then(res => res.text())
          .then(
            (result) => {
              this.setState({ turn: result });
            }
          );
      }, 2000);

    }
  }
  componentWillUnmount() {
    clearInterval(this.interval);
  }
  handleYourPiece(piece) {
    if (piece.color === "White" && this.state.turn % 2 === 0) {
      this.setState({ yourPiece: piece });
    }
    else if (piece.color === "Black" && this.state.turn % 2 === 1) {
      this.setState({ yourPiece: piece });
    }
  }
  handleThierPiece(piece) {
    this.setState({ thierPiece: piece });
  }
  getYourPiece() {
    return this.state.yourPiece;
  }
  getThierPiece() {
    return this.state.thierPiece;
  }
  getBoard() {
    return this.state.board;
  }
  handleSubmit() {
    console.log("Your piece: " + this.state.yourPiece.row);
    console.log("Thier Piece: " + this.state.thierPiece.row);
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        userId: this.props.getUserId(),
        yourRow: this.state.yourPiece.row,
        yourCol: this.state.yourPiece.col,
        thierRow: this.state.thierPiece.row,
        thierCol: this.state.thierPiece.col
      })
    };
    this.setState({ yourPiece: null });
    this.setState({ thierPiece: null });
    fetch('http://localhost:5000/ChessAPI/chessMove', requestOptions)
      //.then(response => response.json())
      .then(data => this.setState({ Board: data }));

  }

  render() {
    console.log("here")
    let chessboard, renderedButton, playerTurn;
    if (this.props.getUserId() !== 0 && this.state.board.length === 8) {
      let board = [...this.state.board];
      /*if(this.state.userColor === "Black"){
        console.log("black board getting flipped")
        for (let x = 0; x < 8; x++){
          for(let y = 0; y < 4; y++){
            let temp = board[x][7-y];
            board[x][7-y] = board[x][y]
            board[x][y] = temp
          }
        }
      }*/
      chessboard = board.map((row) => <Row row={row} getBoard={this.getBoard} userColor={this.state.userColor} setYours={this.handleYourPiece} setThiers={this.handleThierPiece} getYours={this.getYourPiece} getThiers={this.getThierPiece} />);
      renderedButton = (this.state.thierPiece != null && this.state.yourPiece != null) ? <button onClick={this.handleSubmit}>Submit Move</button> : <></>
      playerTurn = (this.state.turn % 2 === 0) ? <h2 className="center">White's turn to move.</h2> : <h2 className="center">Black's turn to move.</h2>
    }
    return (<React.Fragment>
      {playerTurn}
      <table className="center">{chessboard}</table>
      <div className="center">{renderedButton}</div>
    </React.Fragment >);
  }
}
