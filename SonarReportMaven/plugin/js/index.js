import React from 'react';
import CnesReportApp from './components/ReportApp';

window.registerExtension('sonarReportMaven/global_page', () => {
    return <ReportApp />
  });