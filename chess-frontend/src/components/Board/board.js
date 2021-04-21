import React from 'react'
import './styles/board.css'
export default class Board extends React.Component {
  height = window.screen.height - 75
  constructor() {
    super();
    this.state = {
      board: {
        boardId : 0,
	      fenNotation : "",
	      whiteId : null,
	      blackId : null,
	      turn : 0
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
    console.log("Your piece: " + this.state.yourPiece.row);
    console.log("Thier Piece: " + this.state.thierPiece.row);
    this.setState({ yourPiece: null });
    this.setState({ thierPiece: null });
  }

  render() {
    console.log(this.props.getUserId());
    
    let chessboard = []   
    
    let board = this.state.board.fenNotation.split(" ")[0];
    board = board.split("/");
    let index = 0;
    board.forEach((r)=>{
      let prerow = r.split("");
      let row = []
      let i = 0;
      prerow.forEach((elem)=>{
        if(isNaN(elem)){
          row[i++] = elem;
        }
        else{
          for(let x = 0; x < parseInt(elem); x++){
            row[i++] = 1;
          }
        }
      })
      chessboard[index++] = row;
    })
    console.log(chessboard);
    let playerTurn = (this.state.board.turn % 2 === 0) ? <h2 className="center">White's turn to move.</h2> : <h2 className="center">Black's turn to move.</h2>
    return (<React.Fragment>
      {playerTurn}
      <table className="center">{chessboard}</table>
    </React.Fragment >);
  }
}
