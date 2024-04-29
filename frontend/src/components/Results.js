import styles from '../styles/results.module.css';

export default function Results(props) {
    return (
        <div>
        { props.dinghies?.map((dinghy) =>
        <div className={styles.result} key={dinghy.id}>
            <h2>{dinghy.name}</h2>
            <ul>
                <li>LOA: {dinghy.length}m</li>
                <li>Beam: {dinghy.beam}m</li>
                <li>Weight: {dinghy.hullWeight}kg</li>
                <li>PY Number: {dinghy.yardstick}</li>
                <li>Rig: {dinghy.rig}</li>
                <li>Crew: {dinghy.crew}</li>
                <li>Sail area: {dinghy.sailArea}m<sup>2</sup></li>
                {dinghy.symmetricSpinnaker || dinghy.asymmetricSpinnaker ? (
                    <li>Spinnaker:
                        <input type="checkbox" disabled="true" defaultChecked={dinghy.symmetricSpinnaker ? (true) : (false)} />Symmetric
                        <input type="checkbox" disabled="true" defaultChecked={dinghy.asymmetricSpinnaker ? (true) : (false)} />Asymmetric
                    </li>
                ) : null}
                {dinghy.symmetricSpinnaker || dinghy.asymmetricSpinnaker ? (
                    <li>Spinnaker area: {dinghy.spinnakerArea}m<sup>2</sup></li>
                ) : null}
                {dinghy.trapeze > 0 ? (<li>Trapeze: {dinghy.trapeze}</li>) : null}
                {dinghy.manufacturer ? (
                    <li>Manufacturer: {dinghy.manufacturer}</li>
                ) : null}
            </ul>
        </div>
        )}
        </div>
    )
}
