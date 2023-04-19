import { React } from "react";
import { Link } from "react-router-dom";

import "./TeamTile.scss";
export const TeamTile = ({ teamName }) => {
  return (
    <Link className="TeamTile" to={`/teams/${teamName}`}>
      <h1 className="teamName">
       {teamName}
      </h1>
    </Link>
  );
};
