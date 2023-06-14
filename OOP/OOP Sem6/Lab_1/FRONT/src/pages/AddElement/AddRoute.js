import React from "react";
import {Link, Redirect} from "react-router-dom";
import * as API from "../../services/API";
import { confirmAlert } from 'react-confirm-alert'; // Import
import TimeTableForm from "../../components/additional-components/TimeTableForm";
import NavBar from "../../components/nav-bar";
import Loading from "../../components/loading";
import styles from "../../styles/General.module.css";
import UserService from "../../services/UserService";

export class AddRoute extends React.Component{

    componentDidMount = () => {}

    render() {
        if (!UserService.isAdmin()) {
            return (<Redirect to={'/'}/>)
        } else {
            return <AddRouteInternal/>
        }
    }
}

class AddRouteInternal extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            id : 0,
            number: 0,
            startTime: "06:00",
            endTime: "23:30",
            interval: 10,
            type: 'Тролейбус',
            timeTable : [],
            stops: [],
        }
        this.handleInputChange = this.handleInputChange.bind(this);
        this.addStop = this.addStop.bind(this);
        this.removeStop = this.removeStop.bind(this);
        this.resetForm = this.resetForm.bind(this);
        this.saveAndContinue = this.saveAndContinue.bind(this);
        this.saveAndExit = this.saveAndExit.bind(this);
        this.handleInputChangeArray = this.handleInputChangeArray.bind(this);
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        this.setState({
            [name]: value
        });
    }

    handleInputChangeArray(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        if(name[0] === 's'){
            let stops = this.state.stops
            let id = parseInt(name.substring(1))
            stops[id] = value
            this.setState({
                stops: stops
            });
        }else{
            let timeTable = this.state.timeTable
            let id = parseInt(name.substring(1))
            timeTable[id] = value
            this.setState({
                timeTable: timeTable
            });
        }

    }

    componentDidMount = () => {

        this.GetStops().then((stops) => {
            this.setState({
                allStops: stops
            }, function () {
                console.log("ST: " + this.state.allStops)
            })

        }).catch((error) => {
            console.log(error);
        });
    }


    async GetStops() {
        return await API.getStops()
    }

    makeStopChoosing(id){
        let optionsList = []
        this.state.allStops.forEach((stop) => {
            if(stop.name === this.state.stops[id]){
                optionsList.push(<option selected value = {stop.id}>{stop.name}</option>)
            } else {
                optionsList.push(<option value = {stop.id}>{stop.name}</option>)
            }

        })
        return (
            <select onChange={this.handleInputChangeArray} name={"s" + id} value={this.state.stops[id]}>
                {optionsList}
            </select>
        )
    }

    makeTTChoosing(id){

        if(id === 0){
            return (<input onChange={this.handleInputChangeArray} min="0" max = "0" type="number" name={"t" + id}
                           value={this.state.timeTable[id]}/>)
        } else {
            return (<input onChange={this.handleInputChangeArray} min="0" type="number" name={"t" + id}
                           value={this.state.timeTable[id]}/>)
        }

    }

    displayStationsAndTT(stops, tt){
        let list = []

        for(let i = 0; i < stops.length; i++){
            list.push(<li>{this.makeStopChoosing(i)} --- {this.makeTTChoosing(i)}</li>)
        }
        return list
    }

    resetForm(){
        console.log("Reset...")

        this.setState({
            id : 0,
            number: 0,
            startTime: "06:00",
            endTime: "23:30",
            interval: 10,
            type: 'Тролейбус',
            timeTable : [],
            stops: [],
        })

    }

    async saveChanges() {
        let isAvailable = this.state.number > 0
        if(isAvailable) {
            if (this.state.stops.length > 1) {
                let newRoute = await API.createRoute(this.state);
                console.log("CREATED: " + this.state.id)
            } else {
                alert("Не можна створити маршрут, в якому менше двох зупинок!")
            }
        } else {
            alert("Номер маршруту має бути більшим від нуля!")
        }
    }

    reload(){

    }

    addStop(){
        let newStop = this.state.allStops[0].id
        let oldStops = this.state.stops.concat(newStop)
        let newTimetable = this.state.timeTable.concat([5.0])
        this.setState({
            stops:oldStops,
            timeTable:newTimetable
        })
    }

    removeStop(){
        let oldStops = this.state.stops
        if(oldStops.length > 0){
            oldStops.pop()
            let newTimetable = this.state.timeTable
            newTimetable.pop()
            this.setState({
                stops:oldStops,
                timeTable:newTimetable
            })
        }
    }

    async saveAndContinue() {
        await this.saveChanges()
    }

    async saveAndExit(){
        this.setState(
            {returnToEditor : true}
        )
        await this.saveChanges()


    }

    render(){
        if(!UserService.isAdmin()){
            alert("You have no admin rights!")
            return (<Redirect to={'/'}/>)
        }
        if(this.state.returnToEditor){
            return (
                <Redirect to={'/edit/routes'}/>
            )
        }
        return (
            <div>
                <NavBar fatherlink={'/edit/routes'}/>

                <div className={styles.MainFormContainer}>
                    <form className={styles.editForm}>
                        <label><b>{"Реєстрація нового маршруту"}</b></label><br/>
                    <label>Номер маршруту: </label><input type="number" className={styles.wideFromInput} value={this.state.number} name="number" onChange={this.handleInputChange}/><br/>
                    <label>Початок руху: </label><input type="text" className={styles.wideFromInput} value={this.state.startTime} name="startTime" onChange={this.handleInputChange}/><br/>
                    <label>Кінець руху: </label><input type="text" className={styles.wideFromInput} value={this.state.endTime} name="endTime" onChange={this.handleInputChange}/><br/>
                    <label>Інтервал: </label><input type="number" className={styles.wideFromInput} value={this.state.interval} min="1" name="interval" onChange={this.handleInputChange}/><br/>
                    <label>Тип маршруту: </label>
                    <select name="type" value={this.state.type} onChange={this.handleInputChange}>
                        <option value="Тролейбус">Тролейбус</option>
                        <option value="Автобус">Автобус</option>
                        <option value="Трамвай">Трамвай</option>
                    </select>
                    <br/>
                    <label>Станції та розклад руху: </label>

                    <ul>{this.displayStationsAndTT(this.state.stops, this.state.timeTable)}</ul>
                    <input type="button" onClick={this.addStop} value="+"/>
                    <input type="button" onClick={this.removeStop} value="-"/>
                    <br/><br/>
                    <input type="button" onClick={this.resetForm} value="Скасувати зміни"/>
                    <input type="button" onClick={this.saveAndContinue} value="Зберегти та додати наступний"/>
                    <input type="button" onClick={this.saveAndExit} value="Зберегти та вийти"/>
                </form>
                </div>
            </div>

        )


    }


}