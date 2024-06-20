import React from "react";

export default function Heading() {
    const styles = {
        h2: {
            display: "table",

            fontFamily: "monospace",
            fontWeight: "bolder",
            fontSize: "250%",
            borderBlock: "2px solid black",
            margin: "auto",
            marginTop: "2%",
            backgroundColor: "#14dc27"

        }
    }
    return (
        <h2 style={styles.h2} align="center" id="authors">Юрьев Александр Апыхин Артем | P33202 | вар. 5542</h2>
    )
}