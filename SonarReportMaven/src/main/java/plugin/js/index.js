import React from "react";
import './style.css';
import ReportApp from './components/global_page/ReportApp';

window.registerExtension("reportindaba/global_page", () => {
	  return <ReportApp />;
	});