/*
 * Copyright (C) 2017-2017 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
const path = require("path");

module.exports = {
  // Define the entry points here. They MUST have the same name as the page_id
  // defined in src/main/java/org/sonarsource/plugins/example/web/MyPluginPageDefinition.java
  entry: {
    // Using Vanilla JS:
    global_page: ["./src/main/java/plugin/js/index.js"]
  },
  output: {
    // The entry point files MUST be shipped inside the final JAR's static/
    // directory.
    path: path.join(__dirname, "../../../target/"),
    filename: "[name].js"
  },
  resolve: {
    modules: [ path.join(__dirname, "plugin/js") ]
  },
  externals: {
    // React 16.8 ships with SonarQube, and should be re-used to avoid 
    // collisions at runtime.
    react: "React",
    "react-dom": "ReactDOM",
    // Register the Sonar* globals as packages, to simplify importing.
    // See src/main/js/common/api.js for more information on what is exposed 
    // in SonarRequest.
    "sonar-request": "SonarRequest",
    // TODO: provide an example
    "sonar-measures": "SonarMeasures",
    // See src/main/js/portfolio_page/components/MeasuresHistory.js for some
    // examples using React components from SonarQube.
    "sonar-components": "SonarComponents"
  },
  module: {
    // Our example uses Babel to transpile our code.
    rules: [
      {
        test: /\.js$/,
        loader: 'babel-loader',
        exclude: '/node_modules/'
      },
      {
          test: /\.css/,
          loader: "style-loader!css-loader!postcss-loader"
        },
        { test: /\.json$/, loader: "json" }
      ]
  }
};
