import React from 'react'
import Row from './Row/row';
import './styles/board.css'
export default class Board extends React.Component {
  height = window.screen.height - 75
  constructor() {
    super();
    this.state = {
      board: {
        boardId: 0,
        fenNotation: "",
        whiteId: null,
        blackId: null,
        turn: 0
      },
      yourPiece: null,
      thierPiece: null,
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
              this.setState({ board: result });
            },
            (error) => {
              console.log("error in fetching board");
            }
          )
      }, 2000);

    }
  }
  componentWillUnmount() {
    clearInterval(this.interval);
  }
  handleYourPiece(piece) {
    this.setState({ yourPiece: piece });
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
    console.log("Your piece: " + this.state.yourPiece.row + " " + this.state.yourPiece.col);
    console.log("Thier Piece: " + this.state.thierPiece.row + " " + this.state.thierPiece.col);
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
    
    let isBlack = this.props.getUserId() === this.state.board.blackId;

    let chessboard = []
    if(this.state.board.boardId !== 0){
      let board = this.state.board.fenNotation.split(" ")[0];
      board = board.split("/");
      if(isBlack){
        board = board.reverse();
        board = board.map(row=> row.split("").reverse().join(""))
      }
      let index = 0;
      board.forEach((r) => {
        let prerow = r.split("");

        let row = []
        let i = 0;
        prerow.forEach((elem) => {
          if (isNaN(elem)) {
            row[i++] = { type: elem, col: i - 1, row: index };
          }
          else {
            let element = parseInt(elem);
            for (let x = 0; x < element; x++) {
              row[i++] = { type: 0, col: i - 1, row: index };
            }
          }
        })
        chessboard[index++] = row;
      })
      /*
      if (isBlack && this.state.board.boardId !== 0) {
        let row = 0;
        let col = 0;
        for (let i = 7; i >= 0; i--) {
          for (let j = 7; i >= 0; j--) {
            let tempType = chessboard[row][col].type;
            chessboard[row][col].type = chessboard[i][j].type;
            chessboard[i][j].type = tempType;
            col++;
          }
          row++;
        }
      }*/

      chessboard = chessboard.map((row) => <Row row={row} userId={this.props.getUserId()} getBoard={this.getBoard} setYours={this.handleYourPiece} setThiers={this.handleThierPiece} getYours={this.getYourPiece} getThiers={this.getThierPiece} />);
    }
    let renderedButton = (this.state.thierPiece != null && this.state.yourPiece != null) ? <button onClick={this.handleSubmit}>Submit Move</button> : <div></div>
    let playerTurn = (this.state.board.turn % 2 === 0) ? <h2 className="center">White's turn to move.</h2> : <h2 className="center">Black's turn to move.</h2>
    return (<React.Fragment>
      {playerTurn}
      <div className="center">{chessboard}</div>
      <div className="center">{renderedButton}</div>
    </React.Fragment >);
  }
}
