
import React from "react";
import { getProjectsList, initiatePluginToken, getBranches } from "../../api/api";
// SonarComponents (referenced as sonar-components here, see the Webpack config)
// exposes React components exposed by SonarQube.
import { DeferredSpinner } from "sonar-components";

export default class ReportApp extends React.PureComponent {

	state = {
	        loading: true,
	        projects: [],
        	tokens: [] ,
        	authors: [] ,
	        branches: [] ,
	        index: 0,
	        isActive: false,
	        values: []
	    };
	
		handleShow = () => {
		      this.setState({
		          isActive: true,
		          values: "Un proyecto"
		      });
	    };

	    onChangeAuthor = (event) => {
	        this.setState({ author: event.target.value })
	    };

	    onChangeProject = (event) => {
	        getBranches(event.target.value).then(branches => {
	            this.setState({ branches: branches });
	        });
	    };
	    
	    handleAddProject = () => {
	        this.setState({
	        	projects: this.state.shareholders.concat([{ name: "" }])
	        });
	      };
	      
        handleChange(event) {
        	alert("HandleEvent");
    	    this.setState({values: this.state.values.concat(event.target.value)});
    	    alert(this.state.values);
    	  }
	      
	    componentDidMount() {
	        initiatePluginToken().then(tokenInfo => {
	            getProjectsList().then(projects => {
	                if (projects.length > 0) {
	                    getBranches(projects[0].key).then(branches => {
	                        this.setState({
	                            loading: false,
	                            projects: projects,
	                            token: tokenInfo.token,
	                            author: tokenInfo.author,
	                            branches: branches
	                        });
	                    });
	                } else {
	                    this.setState({
	                        loading: false,
	                        projects: projects,
	                        token: tokenInfo.token,
	                        author: tokenInfo.author,
	                        branches: []
	                    });
	                }
	            });
	        });
	    }

	    render() {
	        if (this.state.loading) {
	            return <div className="page page-limited"><DeferredSpinner /></div>;
	        }

	        let projectsList = this.state.projects.length > 0
	            && this.state.projects.map((item, i) => {
	                return (
	                    <option key={i} value={item.key}>{item.name}</option>
	                )
	            }, this);

	        let branchesList = this.state.branches.length > 0
	            && this.state.branches.map((item, i) => {
	                return (
	                    <option key={i} value={item.name}>{item.name}</option>
	                )
	            }, this);

	        return (
	            <div class="page-wrapper-simple">
	                <div class="page-simple">
	                    <h1 class="maintenance-title text-center" color="red">INDABA REPORTE</h1>
	                    <form id="generation-form" action="../../../api/reportindaba/global_page" method="get">
	                    	<div class='projectCard'>
		                        <div class='forminput'>
		                            <label for="key" id="keyLabel" class="login-label"><strong>Project key</strong></label>
		                            <select id="key"
		                                name="key"
		                                class="login-input"
		                                onChange={this.onChangeProject} required>
		                                {projectsList}
		                            </select>
		                        </div>
		                        <div class='forminput'>
		                            <label for="branch" id="branchLabel" class="login-label"><strong>Branch key</strong></label>
		                            <select id="branch"
		                                name="branch"
		                                class="login-input" required>
		                                {branchesList}
		                            </select>
		                        </div>
		                        <div class='forminput'>
		                            <label for="author" id="authorLabel" class="login-label"><strong>Author</strong></label>
		                            <input type="text"
		                                id="author"
		                                name="author"
		                                class="login-input"
		                                maxlength="255"
		                                required
		                                placeholder="Report's author" value={this.state.author}
		                                onChange={this.onChangeAuthor} />
		                            <input type="hidden" name="token" id="token_cnesreport" defaultValue={this.state.token} />
		                            <input type="text" value={this.state.values} onChange={this.handleChange} />
		                            </div>
		                    </div>
		                    <button type="button" onClick={this.handleAddProject}>Add</button>
	                        <button type="button" onClick={this.handleShow}>Show</button>
	                        {this.state.isActive ? <div> 
	                        	<input readOnly value={this.state.value} />
		                        <input id="generation" name="generation" type="submit" value="Generar" /><br />
		                        <em class="info-message">This operation may take some time, please wait while the report is being generated.</em>
	                        </div>
	                        : null }
	                    </form>
	                    
	                </div>
	            </div>
	        );
	    }
}
