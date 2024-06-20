import React from "react";
import {InputNumber} from 'primereact/inputnumber';
import {Button} from "belle";
import store from "../../app/store";

function CoordinatesForm(props) {
    const submit = () => {
        let information = {
            "login": store.getState().login,
            "x": props.x_form,
            "y": props.y_form,
            "r": props.r_form
        };
        let body = [];
        for (const inf in information) {
            body.push(inf + "=" + information[inf]);
        }
        console.log(body);
        body = "?" + body.join("&");
        if (props.validate()) {
            fetch("http://localhost:8080/point" + body, {
                method: "POST"
            }).then(response => response.text().then(text => {
                console.log(JSON.parse(text));
                props.setChecks(JSON.parse(text));
                //props.showChecks();
            }))
        }
    }

    return (
        <div>
            <form id="form">
            <div className="nums-field">
                <InputNumber placeholder="Введите X(-5 .. 5)" value={props.y_form} onValueChange={(e) => props.setX(e.value)} mode="decimal"
                             min={-5} max={5}
                             minFractionDigits={0} maxFractionDigits={3} />
                             <br/>
                </div>
                <div className="nums-field">
                <InputNumber placeholder="Введите Y(-3 .. 3)" value={props.y_form} onValueChange={(e) => props.setY(e.value)} mode="decimal"
                             min={-3} max={3}
                             minFractionDigits={0} maxFractionDigits={3} />
                             <br/>
                </div>
                <div className="nums-field">
                <InputNumber placeholder="Введите R(-5 .. 3)" value={props.y_form} onValueChange={(e) => props.setR(e.value)} mode="decimal"
                             min={0} max={5}
                             minFractionDigits={0} maxFractionDigits={3} />
                             <br/>
                </div>
                <div className="nums-field">
                <Button primary type="button" onClick={submit} icon="pi">OK</Button>
                </div>
            </form>
        </div>
    )
}

export default CoordinatesForm
