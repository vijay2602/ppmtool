import "./App.css";
import React, { Component } from "react";
import Dashboard from "./components/Dashboard";
import Header from "./components/layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/projects/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/projects/UpdateProject";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <Header />
          <Route path="/dashboard" component={Dashboard} />
          <Route path="/addProject" component={AddProject} />
          <Route path="/addProjectTask/:id" component={AddProjectTask} />
          <Route path="/projectBoard/:id" component={ProjectBoard} />
         
          <Route path="/updateProject/:id" component={UpdateProject} />
        </Router>
      </Provider>
    );
  }
}

export default App;
