import React from "react";
import {Link, Redirect} from "react-router-dom";
import * as API from "../../services/API";
import TimeTableForm from "../../components/additional-components/TimeTableForm";
import NavBar from "../../components/nav-bar";
import Loading from "../../components/loading";
import styles from "../../styles/General.module.css";
import UserService from "../../services/UserService";

export class EditRoute extends React.Component{


    componentDidMount = () => {

    }

    render() {
        if (!UserService.isAdmin()) {
            return (<Redirect to={'/'}/>)
        } else {
            return <EditRouteInternal/>
        }
    }
}

export class EditRouteInternal extends React.Component{
    constructor(props) {
        super(props);
        let id = this.getRouteId(window.location.href)
        this.state = {
            id: id,
            oldNumber : -1,
            number: -1,
            startTime: "06:40",
            endTime: "00:10",
            interval: 6,
            type: 'Тролейбус',
            stops: new Map()
        }
        this.handleInputChange = this.handleInputChange.bind(this);
        this.addStop = this.addStop.bind(this);
        this.removeStop = this.removeStop.bind(this);
        this.resetForm = this.resetForm.bind(this);
        this.deleteElement = this.deleteElement.bind(this);
        this.confirmedDelete = this.confirmedDelete.bind(this);
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

    getRouteId(url){
        let id = url.lastIndexOf('=')
        let numberStr = url.substring(id + 1)
        return parseInt(numberStr)
    }

    getType(number){
        switch (number) {
            case 1:
                return "Тролейбус";
            case 2:
                return "Автобус";
            case 3:
                return "Трамвай";
            default:
                return "Тролейбус";
        }
    }

    displayTime(timeStr){
        if(timeStr === undefined || timeStr === null){
            return '--:--'
        }
        return timeStr.toString().substring(11,16)
    }

    resetValues(route){
        let stopsNames = route.stops
        let tt = [0].concat(route.timetable)
        this.setState({
            id : route.id,
            number : route.routeNumber,
            oldNumber : route.routeNumber,
            startTime : this.displayTime(route.startTime),
            endTime : this.displayTime(route.endTime),
            interval : route.interval,
            type: this.getType(route.routeType),
            stops : stopsNames,
            timeTable: tt,
            incorrectRoute : false,
            returnToEditor: false,
            confirmDelete : false,
        }, function () {
            console.log("ST: " + this.state.startTime)
        })
    }

    componentDidMount = () => {
        this.GetRoute().then((routes) => {
            console.log(routes)
            this.resetValues(routes)
        }).catch((error) => {
            console.log(error);
        });

        this.GetStops().then((stops) => {
            this.setState({
               allStops: stops,
                counted: true
            }, function () {
                console.log("ST: " + this.state.startTime)
            })

        }).catch((error) => {
            console.log(error);
        });
    }

    async GetRoute() {
        return await API.getRouteById(this.state.id)
    }

    async GetStops() {
        return await API.getStops()
    }

    AddButton(){
        return (
            <div>
                <TimeTableForm currentId = {this.state.number}/>
            </div>
        )
    }

    makeStopChoosing(id){
        let optionsList = []
        console.log("Stops: " + this.state.stops)
        this.state.allStops.forEach((stop) => {

            if(stop.id === this.state.stops[id]){
                console.log(stop)
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
        this.GetRoute().then((routes) => {
            if(routes.length === 0){
                this.setState({
                    incorrectRoute: true
                })
            } else {
               this.resetValues(routes)
            }

        }).catch((error) => {
            console.log(error);
        });

    }

    deleteElement(){
        this.setState({
            confirmDelete : true
        })
    };

    confirmedDelete(){
        API.deleteRoute(this.state.id, this.state)
        this.setState({
            returnToEditor : true
        })
    };

    async saveChanges() {
        let isAvailable = this.state.number > 0
        if(isAvailable) {
            if (this.state.stops.length > 1) {
                let newRoute = await API.updateRoute(this.state);
                if (newRoute === undefined || newRoute.state === "rejected") {
                    alert("Маршрут номер " + this.state.number + " вже існує!")
                }
            } else {
                alert("Не можна створити маршрут, в якому менше двох зупинок!")
            }
        } else {
            alert("Номер маршруту має бути більшим від нуля!")
        }
    }
    async saveAndContinue(){
        await this.saveChanges()
    }

    async saveAndExit(){
        await this.saveChanges()
        this.setState(
            {returnToEditor : true}
        )

    }

    addStop(){
        let newStop = this.state.allStops[0].name
        let oldStops = this.state.stops.concat(newStop)
        let newTimetable = this.state.timeTable.concat([[5.0]])
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
        if(this.state.confirmDelete){
            return (
                <div>
                    <NavBar fatherlink={'/edit/routes'}/>
                    <form>
                        <label>{"Підтвердження видалення маршруту номер " + this.state.number}</label><br/>
                        <input type="button" onClick={this.resetForm} value="Скасувати видалення"/>
                        <input type="button" onClick={this.confirmedDelete} value="Видалити елемент"/>
                    </form>
                </div>
            )
        }
        if(this.state.incorrectRoute){
            return (
                <div>
                    <NavBar fatherlink={'/edit/routes'}/>
                    {this.AddButton()}
                    <h1>Маршуруту з номером {this.state.number} не знайдено.</h1>
                </div>
            )
        }
        if(this.state.counted) {


            return (

                <div>
                    <NavBar fatherlink={'/edit/routes'}/>
                    <div className={styles.MainFormContainer}>
                        <form className={styles.editForm}>
                            <label><b>{"Редагування маршруту номер " + this.state.number}</b></label><br/>
                            <label>Номер маршруту: </label><input type="number" className={styles.wideFromInput}
                                                                  value={this.state.number} name="number"
                                                                  onChange={this.handleInputChange}/><br/>
                            <label>Початок руху: </label><input type="text" className={styles.wideFromInput}
                                                                value={this.state.startTime} name="startTime"
                                                                onChange={this.handleInputChange}/><br/>
                            <label>Кінець руху: </label><input type="text" className={styles.wideFromInput}
                                                               value={this.state.endTime} name="endTime"
                                                               onChange={this.handleInputChange}/><br/>
                            <label>Інтервал: </label><input type="number" className={styles.wideFromInput}
                                                            value={this.state.interval} min="0" name="interval"
                                                            onChange={this.handleInputChange}/><br/>
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
                            <div className={styles.AllMyButtons}>
                                <input type="button" onClick={this.resetForm} value="Скасувати зміни"/>
                                <input type="button" onClick={this.deleteElement} value="Видалити елемент"/>
                                <input type="button" onClick={this.saveAndContinue} value="Зберегти та продовжити"/>
                                <input type="button" onClick={this.saveAndExit} value="Зберегти та вийти"/>
                            </div>
                        </form>
                    </div>
                </div>

            )
        } else {
            return <Loading/>
        }

    }


}