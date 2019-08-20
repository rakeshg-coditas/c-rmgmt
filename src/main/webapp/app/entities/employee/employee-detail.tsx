import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEmployeeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EmployeeDetail extends React.Component<IEmployeeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { employeeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Employee [<b>{employeeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="email">Email</span>
            </dt>
            <dd>{employeeEntity.email}</dd>
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{employeeEntity.name}</dd>
            <dt>
              <span id="skills">Skills</span>
            </dt>
            <dd>{employeeEntity.skills}</dd>
            <dt>
              <span id="projects">Projects</span>
            </dt>
            <dd>{employeeEntity.projects}</dd>
            <dt>
              <span id="picture">Picture</span>
            </dt>
            <dd>{employeeEntity.picture}</dd>
            <dt>
              <span id="skill_category">Skill Category</span>
            </dt>
            <dd>{employeeEntity.skill_category}</dd>
            <dt>
              <span id="report_to">Report To</span>
            </dt>
            <dd>{employeeEntity.report_to}</dd>
            <dt>
              <span id="team">Team</span>
            </dt>
            <dd>{employeeEntity.team}</dd>
            <dt>
              <span id="career_start_date">Career Start Date</span>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.career_start_date} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="joining_date">Joining Date</span>
            </dt>
            <dd>
              <TextFormat value={employeeEntity.joining_date} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="role">Role</span>
            </dt>
            <dd>{employeeEntity.role}</dd>
            <dt>
              <span id="employeeId">Employee Id</span>
            </dt>
            <dd>{employeeEntity.employeeId}</dd>
            <dt>
              <span id="billable">Billable</span>
            </dt>
            <dd>{employeeEntity.billable}</dd>
            <dt>
              <span id="officeLocation">Office Location</span>
            </dt>
            <dd>{employeeEntity.officeLocation}</dd>
            <dt>
              <span id="employmentType">Employment Type</span>
            </dt>
            <dd>{employeeEntity.employmentType}</dd>
          </dl>
          <Button tag={Link} to="/entity/employee" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/employee/${employeeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ employee }: IRootState) => ({
  employeeEntity: employee.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EmployeeDetail);
