const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const ManifestReplacePlugin = require('webpack-manifest-replace-plugin');
const OptimizeCssAssetsPlugin = require('optimize-css-assets-webpack-plugin');
const TerserJsPlugin = require('terser-webpack-plugin');
const FixStyleOnlyEntriesPlugin = require("webpack-fix-style-only-entries");

module.exports = {
    mode: 'production',
    output: {
        filename: '[name]-[chunkhash].js',
        path: path.resolve(__dirname, 'target/classes/static/assets/'),
    },
    optimization: {
        minimizer: [
            new TerserJsPlugin({
                sourceMap: false,
                cache: true,
                parallel: true,
                terserOptions: {
                    compress: {
                        drop_console: true,
                        drop_debugger: true,
                        unused: true,
                        dead_code: true,
                    },
                    output: {
                        beautify: false,
                        comments: false,
                    },
                    warnings: false,
                },
            }), new OptimizeCssAssetsPlugin({
                cssProcessorOptions: {
                    safe: true,
                },
            })],
    },
    plugins: [
        new FixStyleOnlyEntriesPlugin(),
        new MiniCssExtractPlugin({filename: '[name]-[contenthash].css'}),
        new ManifestReplacePlugin({
            include: path.resolve(__dirname, 'src/main/resources/templates'),
            test: /\.(jsp|htm|html)$/,
            outputDir: path.resolve(__dirname, 'target/test/classes/templates'),
        }),
    ],
};