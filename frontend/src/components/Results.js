export default function Results(props) {
    return (
        <div>
        { props.dinghies.map((dinghy) =>
        <div key={dinghy.id}>
            <h3>{dinghy.name}</h3>
            <ul>
                <li>LOA: {dinghy.length}m</li>
                <li>Beam: {dinghy.beam}m</li>
                <li>Weight: {dinghy.hullWeight}kg</li>
                <li>PY Number: {dinghy.yardstick}</li>
                <li>Rig: {dinghy.rig}</li>
                <li>Crew: {dinghy.crew}</li>
                <li>Sail area: {dinghy.sailArea}m<sup>2</sup></li>
                {dinghy.symmetricSpinnaker || dinghy.asymmetricSpinnaker ? (
                    <div>
                        <li>Spinnaker:
                            <input type="checkbox" disabled="true" defaultChecked={dinghy.symmetricSpinnaker ? (true) : (false)} />Symmetric
                            <input type="checkbox" disabled="true" defaultChecked={dinghy.asymmetricSpinnaker ? (true) : (false)} />Asymmetric
                        </li>
                        <li>Spinnaker area: {dinghy.spinnakerArea}m<sup>2</sup></li>
                    </div>
                ) : null}
                {dinghy.trapeze > 0 ? (<li>Trapeze: {dinghy.trapeze}</li>) : null}
                <li>Manufacturer: {dinghy.manufacturer}</li>
            </ul>
        </div>
        )}
        </div>
    )
}
