import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEmployeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEmployeeUpdateState {
  isNew: boolean;
}

export class EmployeeUpdate extends React.Component<IEmployeeUpdateProps, IEmployeeUpdateState> {
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
      const { employeeEntity } = this.props;
      const entity = {
        ...employeeEntity,
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
    this.props.history.push('/entity/employee');
  };

  render() {
    const { employeeEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cRmgmtApp.employee.home.createOrEditLabel">Create or edit a Employee</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : employeeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="employee-id">ID</Label>
                    <AvInput id="employee-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="emailLabel" for="employee-email">
                    Email
                  </Label>
                  <AvField id="employee-email" type="text" name="email" />
                </AvGroup>
                <AvGroup>
                  <Label id="nameLabel" for="employee-name">
                    Name
                  </Label>
                  <AvField id="employee-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="skillsLabel" for="employee-skills">
                    Skills
                  </Label>
                  <AvField id="employee-skills" type="text" name="skills" />
                </AvGroup>
                <AvGroup>
                  <Label id="projectsLabel" for="employee-projects">
                    Projects
                  </Label>
                  <AvField id="employee-projects" type="text" name="projects" />
                </AvGroup>
                <AvGroup>
                  <Label id="pictureLabel" for="employee-picture">
                    Picture
                  </Label>
                  <AvField id="employee-picture" type="text" name="picture" />
                </AvGroup>
                <AvGroup>
                  <Label id="skill_categoryLabel" for="employee-skill_category">
                    Skill Category
                  </Label>
                  <AvField id="employee-skill_category" type="text" name="skill_category" />
                </AvGroup>
                <AvGroup>
                  <Label id="report_toLabel" for="employee-report_to">
                    Report To
                  </Label>
                  <AvField id="employee-report_to" type="text" name="report_to" />
                </AvGroup>
                <AvGroup>
                  <Label id="teamLabel" for="employee-team">
                    Team
                  </Label>
                  <AvField id="employee-team" type="text" name="team" />
                </AvGroup>
                <AvGroup>
                  <Label id="career_start_dateLabel" for="employee-career_start_date">
                    Career Start Date
                  </Label>
                  <AvField id="employee-career_start_date" type="date" className="form-control" name="career_start_date" />
                </AvGroup>
                <AvGroup>
                  <Label id="joining_dateLabel" for="employee-joining_date">
                    Joining Date
                  </Label>
                  <AvField id="employee-joining_date" type="date" className="form-control" name="joining_date" />
                </AvGroup>
                <AvGroup>
                  <Label id="roleLabel" for="employee-role">
                    Role
                  </Label>
                  <AvField id="employee-role" type="text" name="role" />
                </AvGroup>
                <AvGroup>
                  <Label id="employeeIdLabel" for="employee-employeeId">
                    Employee Id
                  </Label>
                  <AvField id="employee-employeeId" type="text" name="employeeId" />
                </AvGroup>
                <AvGroup>
                  <Label id="billableLabel" for="employee-billable">
                    Billable
                  </Label>
                  <AvField id="employee-billable" type="text" name="billable" />
                </AvGroup>
                <AvGroup>
                  <Label id="officeLocationLabel" for="employee-officeLocation">
                    Office Location
                  </Label>
                  <AvField id="employee-officeLocation" type="text" name="officeLocation" />
                </AvGroup>
                <AvGroup>
                  <Label id="employmentTypeLabel" for="employee-employmentType">
                    Employment Type
                  </Label>
                  <AvField id="employee-employmentType" type="text" name="employmentType" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/employee" replace color="info">
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
  employeeEntity: storeState.employee.entity,
  loading: storeState.employee.loading,
  updating: storeState.employee.updating,
  updateSuccess: storeState.employee.updateSuccess
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
)(EmployeeUpdate);
