import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Client from './client';
import Notification from './notification';
import Project from './project';
import Employee from './employee';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/client`} component={Client} />
      <ErrorBoundaryRoute path={`${match.url}/notification`} component={Notification} />
      <ErrorBoundaryRoute path={`${match.url}/project`} component={Project} />
      <ErrorBoundaryRoute path={`${match.url}/employee`} component={Employee} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
