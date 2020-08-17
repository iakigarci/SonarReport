
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
        loading: false,
        data
      });
    });
  }

  render() {
    return (
      <div className="page page-limited">
        <h1>asdads</h1>
      </div>
    );
  }
}
