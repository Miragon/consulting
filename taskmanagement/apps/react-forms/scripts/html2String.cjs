// eslint-disable-next-line @typescript-eslint/no-var-requires,no-undef
const fs= require('node:fs');
// eslint-disable-next-line @typescript-eslint/no-var-requires,no-undef
const path = require('node:path');

// eslint-disable-next-line no-undef
const inputPath = path.resolve(__dirname, "../dist/index.html");
const outputPath = path.resolve(__dirname, "../dist/index.txt");

fs.readFile(inputPath, "utf8", (err, data) => {
    if (err) {
        console.error(err);
        return;
    }

    const lines = data.split(/\r?\n/);
    const trimmed = lines.map((line) => line.trim());

    let sanitized = trimmed.join("");
    sanitized = sanitized.replace(/\\/g, "\\\\");
    sanitized = sanitized.replace(/"/g, '\\"');

    fs.writeFile(outputPath, sanitized, "utf8", (err) => {
        if (err) {
            console.error(err);
        }
    });
});
