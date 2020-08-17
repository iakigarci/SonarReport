
import React from "react";
// SonarComponents (referenced as sonar-components here, see the Webpack config)
// exposes React components exposed by SonarQube.
import { DeferredSpinner } from "sonar-components";

export default class ReportApp extends React.PureComponent {

  state = {
    loading: true,
    data: []
  };

  componentDidMount() {
    loadPage(this.props.project).then(data => {
      this.setState({
        loading: false
      });
    });
  }

  render() {
	if (this.state.loading) {
	     return <div className="page page-limited"><DeferredSpinner /></div>;
	}
	
    return (
    		<div class="page-wrapper-simple">
            <div class="page-simple">
                <h1 class="maintenance-title text-center">Generate a report</h1>
                <form id="generation-form">
                    <div class='forminput'>
                        <label for="key" id="keyLabel" class="login-label"><strong>Project key</strong></label>
                        
                    </div>
                    
                    <br />
                    <input id="generation" name="generation" type="submit" value="Generate" /><br />
                </form>
            </div>
        </div>
    );
  }
}
