import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './project.reducer';
import { IProject } from 'app/shared/model/project.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProjectUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IProjectUpdateState {
  isNew: boolean;
}

export class ProjectUpdate extends React.Component<IProjectUpdateProps, IProjectUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { projectEntity } = this.props;
      const entity = {
        ...projectEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/project');
  };

  render() {
    const { projectEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cRmgmtApp.project.home.createOrEditLabel">Create or edit a Project</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : projectEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="project-id">ID</Label>
                    <AvInput id="project-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="project-name">
                    Name
                  </Label>
                  <AvField id="project-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="clientLabel" for="project-client">
                    Client
                  </Label>
                  <AvField id="project-client" type="text" name="client" />
                </AvGroup>
                <AvGroup>
                  <Label id="billing_typeLabel" for="project-billing_type">
                    Billing Type
                  </Label>
                  <AvField id="project-billing_type" type="text" name="billing_type" />
                </AvGroup>
                <AvGroup>
                  <Label id="teamLabel" for="project-team">
                    Team
                  </Label>
                  <AvField id="project-team" type="text" name="team" />
                </AvGroup>
                <AvGroup>
                  <Label id="leadLabel" for="project-lead">
                    Lead
                  </Label>
                  <AvField id="project-lead" type="text" name="lead" />
                </AvGroup>
                <AvGroup>
                  <Label id="skillsLabel" for="project-skills">
                    Skills
                  </Label>
                  <AvField id="project-skills" type="text" name="skills" />
                </AvGroup>
                <AvGroup>
                  <Label id="start_dateLabel" for="project-start_date">
                    Start Date
                  </Label>
                  <AvField id="project-start_date" type="date" className="form-control" name="start_date" />
                </AvGroup>
                <AvGroup>
                  <Label id="end_dateLabel" for="project-end_date">
                    End Date
                  </Label>
                  <AvField id="project-end_date" type="date" className="form-control" name="end_date" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/project" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  projectEntity: storeState.project.entity,
  loading: storeState.project.loading,
  updating: storeState.project.updating,
  updateSuccess: storeState.project.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProjectUpdate);
