import { faChessBishop, faChessKing, faChessKnight, faChessPawn, faChessQueen, faChessRook } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react'
import './styles/tile.css'
export default class Tile extends React.Component {
    constructor() {
        super();
        this.state = {
        }
        this.handlePieceClick = this.handlePieceClick.bind(this);
    }
    handlePieceClick(e) { 
        
        let tile = this.props.tile;
        let yours = this.props.getYours();
        console.log(this.props.userId);
        console.log(this.props.getBoard().whiteId)
        let isWhiteUser = this.props.userId === this.props.getBoard().whiteId;
        if(isWhiteUser){
            console.log("white move")
            if(isNaN(tile.type) && tile.type === tile.type.toUpperCase()){
                this.props.setYours(tile)
                this.props.setThiers(null);
            }
            else if(yours !== null){
                this.props.setThiers(tile)
            }
        }
        else{
            console.log("black move")
            if(isNaN(tile.type) && tile.type === tile.type.toLowerCase()){
                this.props.setYours(tile)
                this.props.setThiers(null);
            }
            else if(yours !== null){
                this.props.setThiers(tile)
            }
        }
    }
    render() {
        const tile = this.props.tile;
        let icon;

        let square;
        switch (tile.type) {
            case 'P':
                icon = <FontAwesomeIcon size="2x" icon={faChessPawn} />
                square = <div className="whitepiece">{icon}</div>
                break
            case 'N':
                icon = <FontAwesomeIcon size="2x" icon={faChessKnight} />
                square = <div className="whitepiece">{icon}</div>
                break
            case 'B':
                icon = <FontAwesomeIcon size="2x" icon={faChessBishop} />
                square = <div className="whitepiece">{icon}</div>
                break
            case 'R':
                icon = <FontAwesomeIcon size="2x" icon={faChessRook} />
                square = <div className="whitepiece">{icon}</div>
                break
            case 'Q':
                icon = <FontAwesomeIcon size="2x" icon={faChessQueen} />
                square = <div className="whitepiece">{icon}</div>
                break
            case 'K':
                icon = <FontAwesomeIcon size="2x" icon={faChessKing} />
                square = <div className="whitepiece">{icon}</div>
                break
            case 'p':
                icon = <FontAwesomeIcon size="2x" icon={faChessPawn} />
                square = <div className="blackpiece">{icon}</div>
                break
            case 'n':
                icon = <FontAwesomeIcon size="2x" icon={faChessKnight} />
                square = <div className="blackpiece">{icon}</div>
                break
            case 'b':
                icon = <FontAwesomeIcon size="2x" icon={faChessBishop} />
                square = <div className="blackpiece">{icon}</div>
                break
            case 'r':
                icon = <FontAwesomeIcon size="2x" icon={faChessRook} />
                square = <div className="blackpiece">{icon}</div>
                break
            case 'q':
                icon = <FontAwesomeIcon size="2x" icon={faChessQueen} />
                square = <div className="blackpiece">{icon}</div>
                break
            case 'k':
                icon = <FontAwesomeIcon size="2x" icon={faChessKing} />
                square = <div className="blackpiece">{icon}</div>
                break
        }
        let tileColor = ((tile.row * 7 + tile.col) % 2 === 0) ? "whitetile" : "blacktile";
        let selected = ""
        if(this.props.getYours() !== null && this.props.getYours().row === tile.row && this.props.getYours().col === tile.col) selected = "yourPiece";
        if(this.props.getThiers() !== null && this.props.getThiers().row === tile.row && this.props.getThiers().col === tile.col) selected = "thierPiece";
        return (
            <div className={"tab " + tileColor} id={selected} onClick={this.handlePieceClick}>{square}</div>
        );
    }
}