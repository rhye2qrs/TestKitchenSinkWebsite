import React from "react";
import { Row, Col } from "react-bootstrap";
import RoleBadge from "main/components/Profile/RoleBadge";
import { useCurrentUser } from "main/utils/user";
import BasicLayout from "main/layouts/BasicLayout/BasicLayout";

import ReactJson from "react-json-view";
const ProfilePage = () => {

    const { data: currentUser } = useCurrentUser();

    if (!currentUser.loggedIn) {
        return (
            <p>Not logged in.</p>
        )
    }

    const { email, pictureUrl, fullName } = currentUser.root.user;
    return (
        <BasicLayout>
            <Row className="align-items-center profile-header mb-5 text-center text-md-left">
                <Col md={2}>
                    <img
                        src={pictureUrl}
                        alt="Profile"
                        className="rounded-circle img-fluid profile-picture mb-3 mb-md-0"
                    />
                </Col>
                <Col md>
                    <h2>{fullName}</h2>
                    <p className="lead text-muted">{email}</p>
                    <RoleBadge role={"USER"} currentUser={currentUser}/>
                    <RoleBadge role={"MEMBER"} currentUser={currentUser}/>
                    <RoleBadge role={"ADMIN"} currentUser={currentUser}/>
                </Col>
            </Row>
            <Row className="text-left">
                <ReactJson src={currentUser.root} />
            </Row>
        </BasicLayout>
    );
};

export default ProfilePage;
